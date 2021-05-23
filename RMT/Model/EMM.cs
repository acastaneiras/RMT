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
		private const int SUBSTRING_OFFSET = 1;
		private const int FILEPATH_SUBSTRING_OFFSET = 3;
		private string filePath;
		private PMD [] PMDs ;

        public string FilePath { get => filePath; set => filePath = value; }
        internal PMD [] Pmds { get => PMDs; }

        public EMM(string filePath = "")
        {
            this.filePath = filePath;
        }
		
		// Finds relevant sections (object list and material list) 
		// of the EMM file and separates them out into PMD object
		// TODO: 
		// -	Optimise
		// -	remove elements from array after they have been accounted for.
		// -	make conditionals better.
		// -	split this shit into multiple functions.
		// -	write better comments.
        public void FetchEMM()
        {
			String[] EMMContents = System.IO.File.ReadAllLines(this.FilePath);
			
			int[] ObjectsLength = Util.getDistanceBetween(EMMContents, "[Object]", "");
			String[] EMMObjects = EMMContents.Skip(ObjectsLength[0]).Take(ObjectsLength[SUBSTRING_OFFSET]).ToArray();
			
			int[] MaterialsLength = Util.getDistanceBetween(EMMContents, "[Effect@MaterialMap]", "");
			String[] EMMMaterials = EMMContents.Skip(MaterialsLength[0]).Take(MaterialsLength[SUBSTRING_OFFSET]).ToArray();

			List<PMD> pmds = new List<PMD>();
			RayMaterial mainMaterial = new RayMaterial();
			bool shown = false;
			foreach (String s1 in EMMObjects) {
				//if s1 is a model Object
				if (s1.StartsWith("Pmd")) {
                    string code = s1.Substring(0, s1.IndexOf(' '));
                    //EMMs always have " = " between the code and filepath
                    string filePath = checkMMDRelativePath(s1.Substring((code.Length + FILEPATH_SUBSTRING_OFFSET), s1.Length - (code.Length + FILEPATH_SUBSTRING_OFFSET)));

					List<Subset> subsets = new List<Subset>();

					RayMaterial subsetMaterial = new RayMaterial();
					int subsetNum = 0;
					bool subsetShown = true;
					foreach (String s2 in EMMMaterials) {

						if (s2.StartsWith(code)) {
                            //everything before " = "
                            string codeType = s2.Substring(code.Length, s2.IndexOf('=') - SUBSTRING_OFFSET);
                            //everything after " = "
                            string codeData = s2.Substring(s2.IndexOf('=') + SUBSTRING_OFFSET, s2.Length - (s2.IndexOf('=') + SUBSTRING_OFFSET));
                            switch (codeType) {
								//"PmdXX " 
									//treat as filepath & make a RayMaterial
								case string a when (a[0] == ' ') : 
									if (!codeData.Contains("none")) {
										mainMaterial = new RayMaterial();
										mainMaterial.FilePath = checkMMDRelativePath(codeData.Substring(SUBSTRING_OFFSET));
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
									int newNum = int.Parse(codeType.Substring((codeType.IndexOf('[') + SUBSTRING_OFFSET), (codeType.IndexOf(']') - (codeType.IndexOf('[') + SUBSTRING_OFFSET))));
									string subsetCodeType = codeType.Substring((codeType.IndexOf(']') + SUBSTRING_OFFSET), (codeType.Length - (codeType.IndexOf(']') + SUBSTRING_OFFSET)));
									//If this is a new subset and is not a .show type add previous subset data to list and reset values
									if (newNum != subsetNum && !subsetCodeType.Contains(".show")) {
											subsetMaterial = new RayMaterial();
											subsetMaterial.FilePath = checkMMDRelativePath(codeData.Substring(SUBSTRING_OFFSET));
											subsets.Add(new Subset(newNum, subsetShown, subsetMaterial));
											subsetNum = newNum;
											subsetShown = true;
									} else {
										if (subsetCodeType[0] == '.') {
											subsetShown = (codeData[0] == 't');
										} else {
											subsetMaterial.FilePath = checkMMDRelativePath(codeData.Substring(SUBSTRING_OFFSET));
										}
									}
									break;
							}
						}
					}
					pmds.Add(new PMD(code, filePath, mainMaterial, shown, subsets.ToArray()));
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
                    CommonFileDialogResult folderResult;
                    do
					{
						folderResult = dialog.ShowDialog();
					} while (folderResult != CommonFileDialogResult.Ok);

					//Save path as property
					Properties.Settings.Default.MMDPath = dialog.FileName;
					Properties.Settings.Default.Save();
					MMDPath = Properties.Settings.Default.MMDPath;
				}
				filePath = MMDPath + Path.DirectorySeparatorChar + filePath;
			}
			return filePath;
		}
	}
}
