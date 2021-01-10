using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Diagnostics;
using System.Text.RegularExpressions;
using System.IO;

namespace RMT.Model
{
	class Subset 
	{
		private int num;
		private bool shown;
		private RayMaterial material;
		
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
		
		public PMD(string code, string filePath, RayMaterial mainMaterial, bool shown, Subset[] subsets){
			this.code = code;
			this.filePath = filePath;
			this.mainMaterial = mainMaterial;
			this.shown = shown;
			this.subsets = subsets;
		}
	}
	
    class EMM
    {
        private String filePath;
		private PMD[] PMDs;

		
        public string FilePath { get => filePath; set => filePath = value; }
		public PMD[] Pmds { get => PMDs; }


        public EMM(string filePath = "")
        {
            this.filePath = filePath;
        }

		public string ping()
		{
			return "pong";
		}
		
		// Finds relevant sections (object list and material list) 
		// of the EMM file and separates them out into PMD object
		// TODO: 
		// -	Optimise - remove elements from array 
		// 		after they have been accounted for.
		// -	make conditionals better.
		// -	split this shit into multiple functions.
		// -	write better comments.
		// -	remove magic numbers.
        public void FetchEMM()
        {
			String[] EMMContents = System.IO.File.ReadAllLines(this.FilePath);
			
			int[] ObjectsLength = getDistanceBetween(EMMContents, "[Object]", "");
			String[] EMMObjects = EMMContents.Skip(ObjectsLength[0]).Take(ObjectsLength[1]).ToArray();
			
			int[] MaterialsLength = getDistanceBetween(EMMContents, "[Effect@MaterialMap]", "");
			String[] EMMMaterials = EMMContents.Skip(MaterialsLength[0]).Take(MaterialsLength[1]).ToArray();
            
			List<PMD> pmds = new List<PMD>();
			string code = "";
			string filePath = "";
			RayMaterial mainMaterial = new RayMaterial();
			bool shown = false;
			
			foreach (String s1 in EMMObjects){
				if (s1.Substring(0, 3) == ("Pmd")) {
					code = s1.Substring(0, 4); //i.e. "Pmd2"
					filePath = s1.Substring(7, s1.Length - 7);
					
					List<Subset> subsets = new List<Subset>();
					int subsetNum = 0;
					string subsetFilePath = "";
					bool subsetShown = true;
					RayMaterial subsetMaterial = new RayMaterial();
					
					foreach (String s2 in EMMMaterials) {
						//Get main material
						if (s2.Substring(0, 5) == code + " ") {
							if (s2.Substring(7, s2.Length - 7)!= "none") {
								mainMaterial.FilePath = System.IO.Path.GetFullPath(s2.Substring(7, s2.Length - 7));
								mainMaterial.FetchMaterial();
							}
						}
						//Get whether main is shown
						if (s2.Substring(0, 9) == code + ".show") {
							shown = (s2.Substring(12, 1) == "t") ? true : false;
						}
						//Get subset data
						if (s2.Substring(0, 5) == code + "[") {
							//If this is a new subset add previous subset data to list and reset values
							if (int.Parse(s2.Substring(5, 1)) != subsetNum) {
								if (subsetFilePath != "") {
									Subset tempSubset = new Subset(subsetNum, subsetShown, subsetMaterial);
									subsets.Add(tempSubset);
								}
								
								subsetNum = int.Parse(s2.Substring(5, 1));
								subsetFilePath = "";
								subsetShown = true;
								subsetMaterial = new RayMaterial();
							} else {
								if (s2.Substring(7, 5) == ".show") {
									subsetShown = (s2.Substring(15, 1) == "t") ? true : false;
								}
								if (s2.Substring(7, 1) == " ") {
									subsetMaterial.FilePath = System.IO.Path.GetFullPath(s2.Substring(10, s2.Length - 10));
									subsetMaterial.FetchMaterial();
								}
							}
						}
					}
					PMD pmd = new PMD(code, filePath, mainMaterial, shown, subsets.ToArray());
					pmds.Add(pmd);
				}
			}
			this.PMDs = pmds.ToArray();
        }
		
		//returns the start point and number of lines of the EMM file relevant to a particular section
		private int[] getDistanceBetween(string[] arr, string element1, string element2)  
		{
			int i,j;
			int[] Length = {0,0};
			for (i = 0; i<(arr.Length); i++) {
                if (arr[i] == element1) {
					for (j = i; j<(arr.Length); j++) {
						if (arr[j] == element2) {
							Length[0] = i;
							Length[1] = j-i;
							return Length;
						}
					}
				}
            }
			return Length;
		}
	}
}
