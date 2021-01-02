using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Diagnostics;
using System.Text.RegularExpressions;
using System.IO;

namespace WpfApp1.Model
{
    class RayMaterial
    {
        //AlbedoMap
        private int albedoMapFrom;
        private int albedoMapUVFlip;
        private int albedoMapApplyScale;
        private int albedoMapApplyDiffuse;
        private int albedoMapApplyMorphColor;
        private String albedoMapFile;
        private String albedo;
        private String albedoLoopNum;
        //AlbedoSub
        private int albedoSubEnable;
        private int albedoSubMapFrom;
        private int albedoSubMapUVFlip;
        private int albedoSubMapApplyScale;
        private String albedoSubMapFile;
        private String albedoSub;
        private String albedoSubLoopNum;
        //Alpha
        private int alphaMapFrom;
        private int alphaMapUVFlip;
        private int alphaMapSwizzle;
        private String alphaMapFile;
        private float alpha;
        private String alphaLoopNum;
        //Normal
        private int normalMapFrom;
        private int normalMapType;
        private int normalMapUVFlip;
        private String normalMapFile;
        private float normal;
        private String normalLoopNum;
        //Normal Sub
        private int normalSubMapFrom;
        private int normalSubMapType;
        private int normalSubMapUVFlip;
        private String normalSubMapFile;
        private float normalSub;
        private String normalSubLoopNum;        
        //Smoothness
        private int smoothnessMapFrom;
        private int smoothnessMapType;
        private int smoothnessMapUVFlip;
        private int smoothnessMapSwizzle;
        private int smoothnessMapApplyScale;
        private String smoothnessMapFile;
        private float smoothness;
        private String smoothnessLoopNum;        
        //Metalness
        private int metalnessMapFrom;
        private int metalnessMapUVFlip;
        private int metalnessMapSwizzle;
        private int metalnessMapApplyScale;
        private String metalnessMapFile;
        private float metalness;
        private String metalnessLoopNum;
        //Specular
        private int specularMapFrom;
        private int specularMapType;
        private int specularMapUVFlip;
        private int specularMapSwizzle;
        private int specularMapApplyScale;
        private String specularMapFile;
        private float specular;
        private String specularLoopNum;
        //Occlusion
        private int occlusionMapFrom;
        private int occlusionMapType;
        private int occlusionMapUVFlip;
        private int occlusionMapSwizzle;
        private int occlusionMapApplyScale;
        private String occlusionMapFile;
        private float occlusion;
        private String occlusionLoopNum;
        //Parallax
        private int parallaxMapFrom;
        private int parallaxMapType;
        private int parallaxMapUVFlip;
        private int parallaxMapSwizzle;
        private String parallaxMapFile;
        private float parallax;
        private String parallaxLoopNum;
        //Emissive
        private int emissiveEnable;
        private int emissiveMapFrom;
        private int emissiveMapUVFlip;
        private int emissiveMapApplyScale;
        private int emissiveMapApplyMorphColor;
        private int emissiveMapApplyMorphIntensity;
        private int emissiveMapApplyMorphBlink;
        private String emissiveMapFile;
        private String emissive;
        private float emissiveBlink;
        private float emissiveIntensity;
        private String emissiveLoopNum;
        //Custom
        private int customEnable;
        //CustomA
        private int customAMapFrom;
        private int customAMapUVFlip;
        private int customAMapColorFlip;
        private int customAMapSwizzle;
        private int customAMapApplyScale;
        private String customAMapFile;
        private float customA;
        private String customALoopNum;
        //CustomB
        private int customBMapFrom;
        private int customBMapUVFlip;
        private int customBMapColorFlip;
        private int customBMapApplyScale;
        private String customBMapFile;
        private String customB;
        private String customBLoopNum;

        //Encapsulation
        public int AlbedoMapFrom { get => albedoMapFrom; set => albedoMapFrom = value; }
        public int AlbedoMapUVFlip { get => albedoMapUVFlip; set => albedoMapUVFlip = value; }
        public int AlbedoMapApplyScale { get => albedoMapApplyScale; set => albedoMapApplyScale = value; }
        public int AlbedoMapApplyDiffuse { get => albedoMapApplyDiffuse; set => albedoMapApplyDiffuse = value; }
        public int AlbedoMapApplyMorphColor { get => albedoMapApplyMorphColor; set => albedoMapApplyMorphColor = value; }
        public string AlbedoMapFile { get => albedoMapFile; set => albedoMapFile = value; }
        public string Albedo { get => albedo; set => albedo = value; }
        public string AlbedoLoopNum { get => albedoLoopNum; set => albedoLoopNum = value; }
        public int AlbedoSubEnable { get => albedoSubEnable; set => albedoSubEnable = value; }
        public int AlbedoSubMapFrom { get => albedoSubMapFrom; set => albedoSubMapFrom = value; }
        public int AlbedoSubMapUVFlip { get => albedoSubMapUVFlip; set => albedoSubMapUVFlip = value; }
        public int AlbedoSubMapApplyScale { get => albedoSubMapApplyScale; set => albedoSubMapApplyScale = value; }
        public string AlbedoSubMapFile { get => albedoSubMapFile; set => albedoSubMapFile = value; }
        public string AlbedoSub { get => albedoSub; set => albedoSub = value; }
        public string AlbedoSubLoopNum { get => albedoSubLoopNum; set => albedoSubLoopNum = value; }
        public int AlphaMapFrom { get => alphaMapFrom; set => alphaMapFrom = value; }
        public int AlphaMapUVFlip { get => alphaMapUVFlip; set => alphaMapUVFlip = value; }
        public int AlphaMapSwizzle { get => alphaMapSwizzle; set => alphaMapSwizzle = value; }
        public string AlphaMapFile { get => alphaMapFile; set => alphaMapFile = value; }
        public float Alpha { get => alpha; set => alpha = value; }
        public string AlphaLoopNum { get => alphaLoopNum; set => alphaLoopNum = value; }
        public int NormalMapFrom { get => normalMapFrom; set => normalMapFrom = value; }
        public int NormalMapType { get => normalMapType; set => normalMapType = value; }
        public int NormalMapUVFlip { get => normalMapUVFlip; set => normalMapUVFlip = value; }
        public string NormalMapFile { get => normalMapFile; set => normalMapFile = value; }
        public float Normal { get => normal; set => normal = value; }
        public string NormalLoopNum { get => normalLoopNum; set => normalLoopNum = value; }
        public int NormalSubMapFrom { get => normalSubMapFrom; set => normalSubMapFrom = value; }
        public int NormalSubMapType { get => normalSubMapType; set => normalSubMapType = value; }
        public int NormalSubMapUVFlip { get => normalSubMapUVFlip; set => normalSubMapUVFlip = value; }
        public string NormalSubMapFile { get => normalSubMapFile; set => normalSubMapFile = value; }
        public float NormalSub { get => normalSub; set => normalSub = value; }
        public string NormalSubLoopNum { get => normalSubLoopNum; set => normalSubLoopNum = value; }
        public int SmoothnessMapFrom { get => smoothnessMapFrom; set => smoothnessMapFrom = value; }
        public int SmoothnessMapType { get => smoothnessMapType; set => smoothnessMapType = value; }
        public int SmoothnessMapUVFlip { get => smoothnessMapUVFlip; set => smoothnessMapUVFlip = value; }
        public int SmoothnessMapSwizzle { get => smoothnessMapSwizzle; set => smoothnessMapSwizzle = value; }
        public int SmoothnessMapApplyScale { get => smoothnessMapApplyScale; set => smoothnessMapApplyScale = value; }
        public string SmoothnessMapFile { get => smoothnessMapFile; set => smoothnessMapFile = value; }
        public float Smoothness { get => smoothness; set => smoothness = value; }
        public string SmoothnessLoopNum { get => smoothnessLoopNum; set => smoothnessLoopNum = value; }
        public int MetalnessMapFrom { get => metalnessMapFrom; set => metalnessMapFrom = value; }
        public int MetalnessMapUVFlip { get => metalnessMapUVFlip; set => metalnessMapUVFlip = value; }
        public int MetalnessMapSwizzle { get => metalnessMapSwizzle; set => metalnessMapSwizzle = value; }
        public int MetalnessMapApplyScale { get => metalnessMapApplyScale; set => metalnessMapApplyScale = value; }
        public string MetalnessMapFile { get => metalnessMapFile; set => metalnessMapFile = value; }
        public float Metalness { get => metalness; set => metalness = value; }
        public string MetalnessLoopNum { get => metalnessLoopNum; set => metalnessLoopNum = value; }
        public int SpecularMapFrom { get => specularMapFrom; set => specularMapFrom = value; }
        public int SpecularMapType { get => specularMapType; set => specularMapType = value; }
        public int SpecularMapUVFlip { get => specularMapUVFlip; set => specularMapUVFlip = value; }
        public int SpecularMapSwizzle { get => specularMapSwizzle; set => specularMapSwizzle = value; }
        public int SpecularMapApplyScale { get => specularMapApplyScale; set => specularMapApplyScale = value; }
        public string SpecularMapFile { get => specularMapFile; set => specularMapFile = value; }
        public float Specular { get => specular; set => specular = value; }
        public string SpecularLoopNum { get => specularLoopNum; set => specularLoopNum = value; }
        public int OcclusionMapFrom { get => occlusionMapFrom; set => occlusionMapFrom = value; }
        public int OcclusionMapType { get => occlusionMapType; set => occlusionMapType = value; }
        public int OcclusionMapUVFlip { get => occlusionMapUVFlip; set => occlusionMapUVFlip = value; }
        public int OcclusionMapSwizzle { get => occlusionMapSwizzle; set => occlusionMapSwizzle = value; }
        public int OcclusionMapApplyScale { get => occlusionMapApplyScale; set => occlusionMapApplyScale = value; }
        public string OcclusionMapFile { get => occlusionMapFile; set => occlusionMapFile = value; }
        public float Occlusion { get => occlusion; set => occlusion = value; }
        public string OcclusionLoopNum { get => occlusionLoopNum; set => occlusionLoopNum = value; }
        public int ParallaxMapFrom { get => parallaxMapFrom; set => parallaxMapFrom = value; }
        public int ParallaxMapType { get => parallaxMapType; set => parallaxMapType = value; }
        public int ParallaxMapUVFlip { get => parallaxMapUVFlip; set => parallaxMapUVFlip = value; }
        public int ParallaxMapSwizzle { get => parallaxMapSwizzle; set => parallaxMapSwizzle = value; }
        public string ParallaxMapFile { get => parallaxMapFile; set => parallaxMapFile = value; }
        public float Parallax { get => parallax; set => parallax = value; }
        public string ParallaxLoopNum { get => parallaxLoopNum; set => parallaxLoopNum = value; }
        public int EmissiveEnable { get => emissiveEnable; set => emissiveEnable = value; }
        public int EmissiveMapFrom { get => emissiveMapFrom; set => emissiveMapFrom = value; }
        public int EmissiveMapUVFlip { get => emissiveMapUVFlip; set => emissiveMapUVFlip = value; }
        public int EmissiveMapApplyScale { get => emissiveMapApplyScale; set => emissiveMapApplyScale = value; }
        public int EmissiveMapApplyMorphColor { get => emissiveMapApplyMorphColor; set => emissiveMapApplyMorphColor = value; }
        public int EmissiveMapApplyMorphIntensity { get => emissiveMapApplyMorphIntensity; set => emissiveMapApplyMorphIntensity = value; }
        public int EmissiveMapApplyMorphBlink { get => emissiveMapApplyMorphBlink; set => emissiveMapApplyMorphBlink = value; }
        public string EmissiveMapFile { get => emissiveMapFile; set => emissiveMapFile = value; }
        public string Emissive { get => emissive; set => emissive = value; }
        public float EmissiveBlink { get => emissiveBlink; set => emissiveBlink = value; }
        public float EmissiveIntensity { get => emissiveIntensity; set => emissiveIntensity = value; }
        public string EmissiveLoopNum { get => emissiveLoopNum; set => emissiveLoopNum = value; }
        public int CustomEnable { get => customEnable; set => customEnable = value; }
        public int CustomAMapFrom { get => customAMapFrom; set => customAMapFrom = value; }
        public int CustomAMapUVFlip { get => customAMapUVFlip; set => customAMapUVFlip = value; }
        public int CustomAMapColorFlip { get => customAMapColorFlip; set => customAMapColorFlip = value; }
        public int CustomAMapSwizzle { get => customAMapSwizzle; set => customAMapSwizzle = value; }
        public int CustomAMapApplyScale { get => customAMapApplyScale; set => customAMapApplyScale = value; }
        public string CustomAMapFile { get => customAMapFile; set => customAMapFile = value; }
        public float CustomA { get => customA; set => customA = value; }
        public string CustomALoopNum { get => customALoopNum; set => customALoopNum = value; }
        public int CustomBMapFrom { get => customBMapFrom; set => customBMapFrom = value; }
        public int CustomBMapUVFlip { get => customBMapUVFlip; set => customBMapUVFlip = value; }
        public int CustomBMapColorFlip { get => customBMapColorFlip; set => customBMapColorFlip = value; }
        public int CustomBMapApplyScale { get => customBMapApplyScale; set => customBMapApplyScale = value; }
        public string CustomBMapFile { get => customBMapFile; set => customBMapFile = value; }
        public string CustomB { get => customB; set => customB = value; }
        public string CustomBLoopNum { get => customBLoopNum; set => customBLoopNum = value; }

        public RayMaterial(){}

        public RayMaterial(int albedoMapFrom, int albedoMapUVFlip, int albedoMapApplyScale, int albedoMapApplyDiffuse, int albedoMapApplyMorphColor, string albedoMapFile, string albedo, string albedoLoopNum, int albedoSubEnable, int albedoSubMapFrom, int albedoSubMapUVFlip, int albedoSubMapApplyScale, string albedoSubMapFile, string albedoSub, string albedoSubLoopNum, int alphaMapFrom, int alphaMapUVFlip, int alphaMapSwizzle, string alphaMapFile, float alpha, string alphaLoopNum, int normalMapFrom, int normalMapType, int normalMapUVFlip, string normalMapFile, float normal, string normalLoopNum, int normalSubMapFrom, int normalSubMapType, int normalSubMapUVFlip, string normalSubMapFile, float normalSub, string normalSubLoopNum, int smoothnessMapFrom, int smoothnessMapType, int smoothnessMapUVFlip, int smoothnessMapSwizzle, int smoothnessMapApplyScale, string smoothnessMapFile, float smoothness, string smoothnessLoopNum, int metalnessMapFrom, int metalnessMapUVFlip, int metalnessMapSwizzle, int metalnessMapApplyScale, string metalnessMapFile, float metalness, string metalnessLoopNum, int specularMapFrom, int specularMapType, int specularMapUVFlip, int specularMapSwizzle, int specularMapApplyScale, string specularMapFile, float specular, string specularLoopNum, int occlusionMapFrom, int occlusionMapType, int occlusionMapUVFlip, int occlusionMapSwizzle, int occlusionMapApplyScale, string occlusionMapFile, float occlusion, string occlusionLoopNum, int parallaxMapFrom, int parallaxMapType, int parallaxMapUVFlip, int parallaxMapSwizzle, string parallaxMapFile, float parallax, string parallaxLoopNum, int emissiveEnable, int emissiveMapFrom, int emissiveMapUVFlip, int emissiveMapApplyScale, int emissiveMapApplyMorphColor, int emissiveMapApplyMorphIntensity, int emissiveMapApplyMorphBlink, string emissiveMapFile, string emissive, float emissiveBlink, float emissiveIntensity, string emissiveLoopNum, int customEnable, int customAMapFrom, int customAMapUVFlip, int customAMapColorFlip, int customAMapSwizzle, int customAMapApplyScale, string customAMapFile, float customA, string customALoopNum, int customBMapFrom, int customBMapUVFlip, int customBMapColorFlip, int customBMapApplyScale, string customBMapFile, string customB, string customBLoopNum)
        {
            this.albedoMapFrom = albedoMapFrom;
            this.albedoMapUVFlip = albedoMapUVFlip;
            this.albedoMapApplyScale = albedoMapApplyScale;
            this.albedoMapApplyDiffuse = albedoMapApplyDiffuse;
            this.albedoMapApplyMorphColor = albedoMapApplyMorphColor;
            this.albedoMapFile = albedoMapFile;
            this.albedo = albedo;
            this.albedoLoopNum = albedoLoopNum;
            this.albedoSubEnable = albedoSubEnable;
            this.albedoSubMapFrom = albedoSubMapFrom;
            this.albedoSubMapUVFlip = albedoSubMapUVFlip;
            this.albedoSubMapApplyScale = albedoSubMapApplyScale;
            this.albedoSubMapFile = albedoSubMapFile;
            this.albedoSub = albedoSub;
            this.albedoSubLoopNum = albedoSubLoopNum;
            this.alphaMapFrom = alphaMapFrom;
            this.alphaMapUVFlip = alphaMapUVFlip;
            this.alphaMapSwizzle = alphaMapSwizzle;
            this.alphaMapFile = alphaMapFile;
            this.alpha = alpha;
            this.alphaLoopNum = alphaLoopNum;
            this.normalMapFrom = normalMapFrom;
            this.normalMapType = normalMapType;
            this.normalMapUVFlip = normalMapUVFlip;
            this.normalMapFile = normalMapFile;
            this.normal = normal;
            this.normalLoopNum = normalLoopNum;
            this.normalSubMapFrom = normalSubMapFrom;
            this.normalSubMapType = normalSubMapType;
            this.normalSubMapUVFlip = normalSubMapUVFlip;
            this.normalSubMapFile = normalSubMapFile;
            this.normalSub = normalSub;
            this.normalSubLoopNum = normalSubLoopNum;
            this.smoothnessMapFrom = smoothnessMapFrom;
            this.smoothnessMapType = smoothnessMapType;
            this.smoothnessMapUVFlip = smoothnessMapUVFlip;
            this.smoothnessMapSwizzle = smoothnessMapSwizzle;
            this.smoothnessMapApplyScale = smoothnessMapApplyScale;
            this.smoothnessMapFile = smoothnessMapFile;
            this.smoothness = smoothness;
            this.smoothnessLoopNum = smoothnessLoopNum;
            this.metalnessMapFrom = metalnessMapFrom;
            this.metalnessMapUVFlip = metalnessMapUVFlip;
            this.metalnessMapSwizzle = metalnessMapSwizzle;
            this.metalnessMapApplyScale = metalnessMapApplyScale;
            this.metalnessMapFile = metalnessMapFile;
            this.metalness = metalness;
            this.metalnessLoopNum = metalnessLoopNum;
            this.specularMapFrom = specularMapFrom;
            this.specularMapType = specularMapType;
            this.specularMapUVFlip = specularMapUVFlip;
            this.specularMapSwizzle = specularMapSwizzle;
            this.specularMapApplyScale = specularMapApplyScale;
            this.specularMapFile = specularMapFile;
            this.specular = specular;
            this.specularLoopNum = specularLoopNum;
            this.occlusionMapFrom = occlusionMapFrom;
            this.occlusionMapType = occlusionMapType;
            this.occlusionMapUVFlip = occlusionMapUVFlip;
            this.occlusionMapSwizzle = occlusionMapSwizzle;
            this.occlusionMapApplyScale = occlusionMapApplyScale;
            this.occlusionMapFile = occlusionMapFile;
            this.occlusion = occlusion;
            this.occlusionLoopNum = occlusionLoopNum;
            this.parallaxMapFrom = parallaxMapFrom;
            this.parallaxMapType = parallaxMapType;
            this.parallaxMapUVFlip = parallaxMapUVFlip;
            this.parallaxMapSwizzle = parallaxMapSwizzle;
            this.parallaxMapFile = parallaxMapFile;
            this.parallax = parallax;
            this.parallaxLoopNum = parallaxLoopNum;
            this.emissiveEnable = emissiveEnable;
            this.emissiveMapFrom = emissiveMapFrom;
            this.emissiveMapUVFlip = emissiveMapUVFlip;
            this.emissiveMapApplyScale = emissiveMapApplyScale;
            this.emissiveMapApplyMorphColor = emissiveMapApplyMorphColor;
            this.emissiveMapApplyMorphIntensity = emissiveMapApplyMorphIntensity;
            this.emissiveMapApplyMorphBlink = emissiveMapApplyMorphBlink;
            this.emissiveMapFile = emissiveMapFile;
            this.emissive = emissive;
            this.emissiveBlink = emissiveBlink;
            this.emissiveIntensity = emissiveIntensity;
            this.emissiveLoopNum = emissiveLoopNum;
            this.customEnable = customEnable;
            this.customAMapFrom = customAMapFrom;
            this.customAMapUVFlip = customAMapUVFlip;
            this.customAMapColorFlip = customAMapColorFlip;
            this.customAMapSwizzle = customAMapSwizzle;
            this.customAMapApplyScale = customAMapApplyScale;
            this.customAMapFile = customAMapFile;
            this.customA = customA;
            this.customALoopNum = customALoopNum;
            this.customBMapFrom = customBMapFrom;
            this.customBMapUVFlip = customBMapUVFlip;
            this.customBMapColorFlip = customBMapColorFlip;
            this.customBMapApplyScale = customBMapApplyScale;
            this.customBMapFile = customBMapFile;
            this.customB = customB;
            this.customBLoopNum = customBLoopNum;
        }



        /*
         * Fetches a .fx ray-mmd material file
         */
        public void FetchMaterial(String materialFile)
        {
            String [] materialContents = System.IO.File.ReadAllLines(materialFile);

            foreach (String line in materialContents)
            {
                AssignMaterialValues(line);
            }
        }


        /*
         * Assigns values to RayMaterial object from fetched file
         */
        private void AssignMaterialValues(string line)
        {
            var integerFetchedValue = -1;
            var stringFetchedValue  = "";
            var floatFetechedValue  = -1.0f;

            switch(line)
            {
                case string a when a.Contains("ALBEDO_MAP_FROM"):
                    integerFetchedValue = RetreiveNumberParamValue(line);
                    this.AlbedoMapFrom = integerFetchedValue > -1 ? integerFetchedValue : this.AlbedoMapFrom;
                    break;
                case string a when a.Contains("ALBEDO_MAP_UV_FLIP"):
                    integerFetchedValue = RetreiveNumberParamValue(line);
                    this.AlbedoMapUVFlip = integerFetchedValue > -1 ? integerFetchedValue : this.AlbedoMapUVFlip;
                    break;
                case string a when a.Contains("ALBEDO_MAP_APPLY_SCALE"):
                    integerFetchedValue = RetreiveNumberParamValue(line);
                    this.AlbedoMapApplyScale = integerFetchedValue > -1 ? integerFetchedValue : this.AlbedoMapApplyScale;
                    break;
                case string a when a.Contains("ALBEDO_MAP_APPLY_DIFFUSE"):
                    integerFetchedValue = RetreiveNumberParamValue(line);
                    this.AlbedoMapApplyDiffuse = integerFetchedValue > -1 ? integerFetchedValue : this.AlbedoMapApplyDiffuse;
                    break;
                case string a when a.Contains("ALBEDO_MAP_APPLY_MORPH_COLOR"):
                    integerFetchedValue = RetreiveNumberParamValue(line);
                    this.AlbedoMapApplyMorphColor = integerFetchedValue > -1 ? integerFetchedValue : this.AlbedoMapApplyMorphColor;
                    break;
                case string a when a.Contains("ALBEDO_MAP_FILE"):
                    stringFetchedValue = RetrieveSubStringBetween(line, '"');
                    this.AlbedoMapFile = !stringFetchedValue.Equals("") ? stringFetchedValue : this.AlbedoMapFile;
                    break;
                case string a when a.Contains("albedo "):
                    stringFetchedValue = RetrieveSubStringBetween(line, '=', true);
                    this.Albedo = !stringFetchedValue.Equals("") ? stringFetchedValue : this.Albedo;
                    break;
                case string a when a.Contains("albedoMapLoopNum"):
                    stringFetchedValue = RetrieveSubStringBetween(line, '=', true);
                    this.AlbedoLoopNum = !stringFetchedValue.Equals("") ? stringFetchedValue : this.AlbedoLoopNum;
                    break;
                case string a when a.Contains("ALBEDO_SUB_ENABLE"):
                    integerFetchedValue = RetreiveNumberParamValue(line);
                    this.AlbedoSubEnable = integerFetchedValue > -1 ? integerFetchedValue : this.AlbedoSubEnable;
                    break;                
                case string a when a.Contains("ALBEDO_SUB_MAP_FROM"):
                    integerFetchedValue = RetreiveNumberParamValue(line);
                    this.AlbedoSubMapFrom = integerFetchedValue > -1 ? integerFetchedValue : this.AlbedoSubMapFrom;
                    break;
                case string a when a.Contains("ALBEDO_SUB_MAP_UV_FLIP"):
                    integerFetchedValue = RetreiveNumberParamValue(line);
                    this.AlbedoSubMapUVFlip = integerFetchedValue > -1 ? integerFetchedValue : this.AlbedoSubMapUVFlip;
                    break;
                case string a when a.Contains("ALBEDO_SUB_MAP_APPLY_SCALE"):
                    integerFetchedValue = RetreiveNumberParamValue(line);
                    this.AlbedoSubMapApplyScale = integerFetchedValue > -1 ? integerFetchedValue : this.AlbedoSubMapApplyScale;
                    break;
                case string a when a.Contains("ALBEDO_SUB_MAP_FILE"):
                    stringFetchedValue = RetrieveSubStringBetween(line, '"');
                    this.AlbedoSubMapFile = !stringFetchedValue.Equals("") ? stringFetchedValue : this.AlbedoSubMapFile;
                    break;
                case string a when a.Contains("albedoSub "):
                    stringFetchedValue = RetrieveSubStringBetween(line, '=', true);
                    this.AlbedoSub = !stringFetchedValue.Equals("") ? stringFetchedValue : this.AlbedoSub;
                    break;
                case string a when a.Contains("albedoSubMapLoopNum"):
                    stringFetchedValue = RetrieveSubStringBetween(line, '=', true);
                    this.AlbedoSubLoopNum = !stringFetchedValue.Equals("") ? stringFetchedValue : this.AlbedoSubLoopNum;
                    break;
            }
        }
        /*
         * Function that saves a material file
         */
        private void SaveMaterial(String materialPath)
        {
            File.WriteAllText(materialPath, this.ToString());
        }


        public override string ToString()
        {
            //TODO
            return base.ToString();
        }



        //UTILS
        private float RetrieveFloatNumber(string line)
        {
            var floatNumber = 1.0;
            var match = Regex.Match(line, @"([-+]?[0-9]*\.?[0-9]+)");
            if (match.Success)
                floatNumber = Convert.ToSingle(match.Groups[1].Value);

            return (float)floatNumber;
        }

        private String RetrieveSubStringBetween(String line, char character, bool excludeLastCharacter = false)
        {
            String substring = line.Substring(line.IndexOf(character));
            if (excludeLastCharacter)
                substring = substring.Remove(substring.Length-1);

            return substring;
        }

        private int RetreiveNumberParamValue(string line)
        {
            String resultString = Regex.Match(line, @"\d+").Value;
            int value = -1;
            int.TryParse(resultString, out value);
            return value;

        }

    }
}
