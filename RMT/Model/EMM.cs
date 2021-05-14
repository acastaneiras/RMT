using System;
using System.Collections.Generic;
using System.Linq;
using System.IO;
using Microsoft.WindowsAPICodePack.Dialogs;
using System.Diagnostics;

namespace RMT.Model
{
    class Subset 
	{
		private int num;
		private bool shown;
		private RayMaterial material;

		public RayMaterial Material { get => material; }
		public int Num { get => num; }
		public bool IsShown { get => shown; }


		public Subset(int num, bool shown, RayMaterial material){
			this.num = num;
			this.shown = shown;
			this.material = material;
		}
	}
	
	class PMD
	{
		private String code;
		private String filePath;
		private RayMaterial mainMaterial;
		private bool shown;
		private Subset[] subsets;
		
		public string Code { get => code; }
		public Subset[] Subsets { get => subsets; }
		public RayMaterial MainMaterial { get => mainMaterial; }
		private String FilePath { get => filePath; }
		public bool IsShown { get => shown; }


		public PMD(string code, string filePath, RayMaterial mainMaterial, bool shown, Subset[] subsets)
        {
			this.code = code;
			this.filePath = filePath;
			this.mainMaterial = mainMaterial;
			this.shown = shown;
			this.subsets = subsets;
		}
	}
	
    public class EMM
    {
        private String filePath;
		private PMD[] PMDs;

        public string FilePath { get => filePath; set => filePath = value; }
        internal PMD[] Pmds { get => PMDs; }

        public EMM(string filePath = "")
        {
            this.filePath = filePath;
        }
		
		// Finds relevant sections (object list and material list) 
		// of the EMM file and separates them out into PMD object
		// TODO: 
		// -	Handle relative file paths
		// -	Optimise - remove elements from array 
		// 		after they have been accounted for.
		// -	make conditionals better.
		// -	split this shit into multiple functions.
		// -	write better comments.
		// -	remove magic numbers.
        public void FetchEMM()
        {
			String[] EMMContents = System.IO.File.ReadAllLines(this.FilePath);
			
			int[] ObjectsLength = Util.getDistanceBetween(EMMContents, "[Object]", "");
			String[] EMMObjects = EMMContents.Skip(ObjectsLength[0]).Take(ObjectsLength[1]).ToArray();
			
			int[] MaterialsLength = Util.getDistanceBetween(EMMContents, "[Effect@MaterialMap]", "");
			String[] EMMMaterials = EMMContents.Skip(MaterialsLength[0]).Take(MaterialsLength[1]).ToArray();
            
			List<PMD> pmds = new List<PMD>();
			string code = "";									//i.e. "Pmd4", "Pmd27"
			string filePath = "";								//either "UserFile\xyz" or "C:/xyz"
			RayMaterial mainMaterial = new RayMaterial();
			bool shown = false;
			
			foreach (String s1 in EMMObjects) {
				//if s1 is a model Object
				if (s1.StartsWith("Pmd")) {
					code = s1.Substring(0, s1.IndexOf(' ')); 
					//EMMs always have " = " between the code and filepath
					filePath = checkMMDRelativePath(s1.Substring((code.Length + 3), s1.Length - (code.Length + 3))); //everything left of "PmdXX = "
					
					List<Subset> subsets = new List<Subset>();
					int subsetNum = 0;
					bool subsetShown = true;
					RayMaterial subsetMaterial = new RayMaterial();
					
					string codeType;
					string codeData;
					
					foreach (String s2 in EMMMaterials) {
						if (s2.StartsWith(code)) {
							//everything before " = "
							codeType = s2.Substring(code.Length, s2.IndexOf('=') - 1);
							//everything after " = "
							codeData = s2.Substring(s2.IndexOf('=') + 1, s2.Length - (s2.IndexOf('=') +1));
							switch (codeType) {
								
								//"PmdXX " 
									//treat as filepath & make a RayMaterial
								case string a when (a[0] == ' ') : 
									if (!codeData.Contains("none")) {
										mainMaterial.FilePath = checkMMDRelativePath(codeData);
										mainMaterial.FetchMaterial();
									}
									break;
									
								//"PmdXX.show"
									//treat as .show and set shown
								case string a when (a[0] == '.') :
									//if the first character after "PmdXX.show = " is 't' then set it to true, otherwise false
									shown = (codeData[0] == 't');
									break;
								
								
								//"PmdXX["
									//treat as subset 
								case string a when (a[0] == '[') :
									int newNum = int.Parse(codeType.Substring((codeType.IndexOf('[') + 1), (codeType.IndexOf(']') - (codeType.IndexOf('[') + 1))));
									string subsetCodeType = codeType.Substring((codeType.IndexOf(']') + 1), (codeType.Length - (codeType.IndexOf(']') + 1)));
									//If this is a new subset add previous subset data to list and reset values
									if (newNum != subsetNum) {
										if (subsetMaterial.FilePath != "") {
											Subset tempSubset = new Subset(subsetNum, subsetShown, subsetMaterial);
											subsets.Add(tempSubset);
										}
										
										subsetNum = newNum;
										subsetShown = true;
										subsetMaterial = new RayMaterial();
									} else {
										
										if (subsetCodeType[0] == '.') {
											subsetShown = (codeData[0] == 't');
										} else {
											subsetMaterial.FilePath = checkMMDRelativePath(codeData);
											subsetMaterial.FetchMaterial();
										}
									}
									break;
							}
						}
					}
					PMD pmd = new PMD(code, filePath, mainMaterial, shown, subsets.ToArray());
					pmds.Add(pmd);
				}
			}
			this.PMDs = pmds.ToArray();
        }
		
		public string checkMMDRelativePath(string filePath) 
		{
			if (!Path.IsPathRooted(filePath)) {
				//if mmd filepath is specified then return it
				//else ask for it
				String MMDPath = Properties.Settings.Default.MMDPath;
				if (MMDPath == null || !Directory.Exists(MMDPath))
                {
					CommonOpenFileDialog dialog = new CommonOpenFileDialog();
					dialog.InitialDirectory = "C:\\";
					dialog.IsFolderPicker = true;
					dialog.Title = "Select MMD's root folder";
					CommonFileDialogResult folderResult = CommonFileDialogResult.None;
					do
					{
						folderResult = dialog.ShowDialog();
					} while (folderResult != CommonFileDialogResult.Ok);

					//Save path as property
					Properties.Settings.Default.MMDPath = dialog.FileName;
					Properties.Settings.Default.Save();
					MMDPath = Properties.Settings.Default.MMDPath;
				}
                if (filePath[0] == ' ')
					filePath = filePath.Substring(1);
				filePath = MMDPath + Path.DirectorySeparatorChar + filePath;
			}
			return filePath;
		}
	}
}
