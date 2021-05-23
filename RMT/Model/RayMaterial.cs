using System.IO;

namespace RMT.Model
{
    class RayMaterial
    {
        private float emissiveIntensity;
        private int customAMapApplyScale;
        private string includeFxsub;

        //Encapsulation
        public int AlbedoMapFrom { get; set; }
        public int AlbedoMapUVFlip { get; set; }
        public int AlbedoMapApplyScale { get; set; }
        public int AlbedoMapApplyDiffuse { get; set; }
        public int AlbedoMapApplyMorphColor { get; set; }
        public string AlbedoMapFile { get; set; }
        public string Albedo { get; set; }
        public string AlbedoLoopNum { get; set; }
        public int AlbedoSubEnable { get; set; }
        public int AlbedoSubMapFrom { get; set; }
        public int AlbedoSubMapUVFlip { get; set; }
        public int AlbedoSubMapApplyScale { get; set; }
        public string AlbedoSubMapFile { get; set; }
        public string AlbedoSub { get; set; }
        public string AlbedoSubLoopNum { get; set; }
        public int AlphaMapFrom { get; set; }
        public int AlphaMapUVFlip { get; set; }
        public int AlphaMapSwizzle { get; set; }
        public string AlphaMapFile { get; set; }
        public float Alpha { get; set; }
        public string AlphaLoopNum { get; set; }
        public int NormalMapFrom { get; set; }
        public int NormalMapType { get; set; }
        public int NormalMapUVFlip { get; set; }
        public string NormalMapFile { get; set; }
        public float Normal { get; set; }
        public string NormalLoopNum { get; set; }
        public int NormalSubMapFrom { get; set; }
        public int NormalSubMapType { get; set; }
        public int NormalSubMapUVFlip { get; set; }
        public string NormalSubMapFile { get; set; }
        public float NormalSub { get; set; }
        public string NormalSubLoopNum { get; set; }
        public int SmoothnessMapFrom { get; set; }
        public int SmoothnessMapType { get; set; }
        public int SmoothnessMapUVFlip { get; set; }
        public int SmoothnessMapSwizzle { get; set; }
        public int SmoothnessMapApplyScale { get; set; }
        public string SmoothnessMapFile { get; set; }
        public float Smoothness { get; set; }
        public string SmoothnessLoopNum { get; set; }
        public int MetalnessMapFrom { get; set; }
        public int MetalnessMapUVFlip { get; set; }
        public int MetalnessMapSwizzle { get; set; }
        public int MetalnessMapApplyScale { get; set; }
        public string MetalnessMapFile { get; set; }
        public float Metalness { get; set; }
        public string MetalnessLoopNum { get; set; }
        public int SpecularMapFrom { get; set; }
        public int SpecularMapType { get; set; }
        public int SpecularMapUVFlip { get; set; }
        public int SpecularMapSwizzle { get; set; }
        public int SpecularMapApplyScale { get; set; }
        public string SpecularMapFile { get; set; }
        public string Specular { get; set; }
        public string SpecularLoopNum { get; set; }
        public int OcclusionMapFrom { get; set; }
        public int OcclusionMapType { get; set; }
        public int OcclusionMapUVFlip { get; set; }
        public int OcclusionMapSwizzle { get; set; }
        public int OcclusionMapApplyScale { get; set; }
        public string OcclusionMapFile { get; set; }
        public float Occlusion { get; set; }
        public string OcclusionLoopNum { get; set; }
        public int ParallaxMapFrom { get; set; }
        public int ParallaxMapType { get; set; }
        public int ParallaxMapUVFlip { get; set; }
        public int ParallaxMapSwizzle { get; set; }
        public string ParallaxMapFile { get; set; }
        public float Parallax { get; set; }
        public string ParallaxLoopNum { get; set; }
        public int EmissiveEnable { get; set; }
        public int EmissiveMapFrom { get; set; }
        public int EmissiveMapUVFlip { get; set; }
        public int EmissiveMapApplyScale { get; set; }
        public int EmissiveMapApplyMorphColor { get; set; }
        public int EmissiveMapApplyMorphIntensity { get; set; }
        public int EmissiveMapApplyBlink { get; set; }
        public string EmissiveMapFile { get; set; }
        public string Emissive { get; set; }
        public float EmissiveBlink { get; set; }
        public float EmissiveIntensity { get => emissiveIntensity; set => emissiveIntensity = value; }
        public string EmissiveLoopNum { get; set; }
        public int CustomEnable { get; set; }
        public int CustomAMapFrom { get; set; }
        public int CustomAMapUVFlip { get; set; }
        public int CustomAMapColorFlip { get; set; }
        public int CustomAMapSwizzle { get; set; }
        public int CustomAMapApplyScale { get => customAMapApplyScale; set => customAMapApplyScale = value; }
        public string CustomAMapFile { get; set; }
        public float CustomA { get; set; }
        public string CustomALoopNum { get; set; }
        public int CustomBMapFrom { get; set; }
        public int CustomBMapUVFlip { get; set; }
        public int CustomBMapColorFlip { get; set; }
        public int CustomBMapApplyScale { get; set; }
        public string CustomBMapFile { get; set; }
        public string CustomB { get; set; }
        public string CustomBLoopNum { get; set; }
        public string IncludeFxsub { get => includeFxsub; set => includeFxsub = value; }
        public string FilePath { get; set; }

        public RayMaterial(int albedoMapFrom = 3, int albedoMapUVFlip = 0, int albedoMapApplyScale = 0, int albedoMapApplyDiffuse = 1, int albedoMapApplyMorphColor = 0, string albedoMapFile = "\"albedo.png\"", string albedo = "1.0", string albedoLoopNum = "float2(1,1)", int albedoSubEnable = 0, int albedoSubMapFrom = 0, int albedoSubMapUVFlip = 0, int albedoSubMapApplyScale = 0, string albedoSubMapFile = "\"albedo.png\"", string albedoSub = "1.0", string albedoSubLoopNum = "float2(1,1)", int alphaMapFrom = 3, int alphaMapUVFlip = 0, int alphaMapSwizzle = 3, string alphaMapFile = "\"alpha.png\"", float alpha = 1.0f, string alphaLoopNum = "float2(1,1)", int normalMapFrom = 0, int normalMapType = 0, int normalMapUVFlip = 0, string normalMapFile = "\"normal.png\"", float normal = 1.0f, string normalLoopNum = "float2(1,1)", int normalSubMapFrom = 0, int normalSubMapType = 0, int normalSubMapUVFlip = 0, string normalSubMapFile = "\"normal.png\"", float normalSub = 1.0f, string normalSubLoopNum = "float2(1,1)", int smoothnessMapFrom = 9, int smoothnessMapType = 0, int smoothnessMapUVFlip = 0, int smoothnessMapSwizzle = 0, int smoothnessMapApplyScale = 0, string smoothnessMapFile = "\"smoothness.png\"", float smoothness = 0.0f, string smoothnessLoopNum = "float2(1,1)", int metalnessMapFrom = 0, int metalnessMapUVFlip = 0, int metalnessMapSwizzle = 0, int metalnessMapApplyScale = 0, string metalnessMapFile = "\"metalness.png\"", float metalness = 0.0f, string metalnessLoopNum = "float2(1,1)", int specularMapFrom = 0, int specularMapType = 0, int specularMapUVFlip = 0, int specularMapSwizzle = 0, int specularMapApplyScale = 0, string specularMapFile = "\"specular.png\"", string specular = "0.5", string specularLoopNum = "float2(1,1)", int occlusionMapFrom = 0, int occlusionMapType = 0, int occlusionMapUVFlip = 0, int occlusionMapSwizzle = 0, int occlusionMapApplyScale = 0, string occlusionMapFile = "\"occlusion.png\"", float occlusion = 1.0f, string occlusionLoopNum = "float2(1,1)", int parallaxMapFrom = 0, int parallaxMapType = 0, int parallaxMapUVFlip = 0, int parallaxMapSwizzle = 0, string parallaxMapFile = "\"height.png\"", float parallax = 1.0f, string parallaxLoopNum = "float2(1,1)", int emissiveEnable = 0, int emissiveMapFrom = 0, int emissiveMapUVFlip = 0, int emissiveMapApplyScale = 0, int emissiveMapApplyMorphColor = 0, int emissiveMapApplyMorphIntensity = 0, int emissiveMapApplyBlink = 0, string emissiveMapFile = "\"emissive.png\"", string emissive = "1.0", float emissiveBlink = 1.0f, float emissiveIntensity = 1.0f, string emissiveLoopNum = "float2(1,1)", int customEnable = 0, int customAMapFrom = 0, int customAMapUVFlip = 0, int customAMapColorFlip = 0, int customAMapSwizzle = 0, int customAMapApplyScale = 0, string customAMapFile = "\"custom.png\"", float customA = 0.0f, string customALoopNum = "float2(1,1)", int customBMapFrom = 0, int customBMapUVFlip = 0, int customBMapColorFlip = 0, int customBMapApplyScale = 0, string customBMapFile = "\"custom.png\"", string customB = "0.0", string customBLoopNum = "float2(1,1)", string includeFxsub = "\"../material_common_2.0.fxsub\"", string filePath = "")
        {
            this.AlbedoMapFrom = albedoMapFrom;
            this.AlbedoMapUVFlip = albedoMapUVFlip;
            this.AlbedoMapApplyScale = albedoMapApplyScale;
            this.AlbedoMapApplyDiffuse = albedoMapApplyDiffuse;
            this.AlbedoMapApplyMorphColor = albedoMapApplyMorphColor;
            this.AlbedoMapFile = albedoMapFile;
            this.Albedo = albedo;
            this.AlbedoLoopNum = albedoLoopNum;
            this.AlbedoSubEnable = albedoSubEnable;
            this.AlbedoSubMapFrom = albedoSubMapFrom;
            this.AlbedoSubMapUVFlip = albedoSubMapUVFlip;
            this.AlbedoSubMapApplyScale = albedoSubMapApplyScale;
            this.AlbedoSubMapFile = albedoSubMapFile;
            this.AlbedoSub = albedoSub;
            this.AlbedoSubLoopNum = albedoSubLoopNum;
            this.AlphaMapFrom = alphaMapFrom;
            this.AlphaMapUVFlip = alphaMapUVFlip;
            this.AlphaMapSwizzle = alphaMapSwizzle;
            this.AlphaMapFile = alphaMapFile;
            this.Alpha = alpha;
            this.AlphaLoopNum = alphaLoopNum;
            this.NormalMapFrom = normalMapFrom;
            this.NormalMapType = normalMapType;
            this.NormalMapUVFlip = normalMapUVFlip;
            this.NormalMapFile = normalMapFile;
            this.Normal = normal;
            this.NormalLoopNum = normalLoopNum;
            this.NormalSubMapFrom = normalSubMapFrom;
            this.NormalSubMapType = normalSubMapType;
            this.NormalSubMapUVFlip = normalSubMapUVFlip;
            this.NormalSubMapFile = normalSubMapFile;
            this.NormalSub = normalSub;
            this.NormalSubLoopNum = normalSubLoopNum;
            this.SmoothnessMapFrom = smoothnessMapFrom;
            this.SmoothnessMapType = smoothnessMapType;
            this.SmoothnessMapUVFlip = smoothnessMapUVFlip;
            this.SmoothnessMapSwizzle = smoothnessMapSwizzle;
            this.SmoothnessMapApplyScale = smoothnessMapApplyScale;
            this.SmoothnessMapFile = smoothnessMapFile;
            this.Smoothness = smoothness;
            this.SmoothnessLoopNum = smoothnessLoopNum;
            this.MetalnessMapFrom = metalnessMapFrom;
            this.MetalnessMapUVFlip = metalnessMapUVFlip;
            this.MetalnessMapSwizzle = metalnessMapSwizzle;
            this.MetalnessMapApplyScale = metalnessMapApplyScale;
            this.MetalnessMapFile = metalnessMapFile;
            this.Metalness = metalness;
            this.MetalnessLoopNum = metalnessLoopNum;
            this.SpecularMapFrom = specularMapFrom;
            this.SpecularMapType = specularMapType;
            this.SpecularMapUVFlip = specularMapUVFlip;
            this.SpecularMapSwizzle = specularMapSwizzle;
            this.SpecularMapApplyScale = specularMapApplyScale;
            this.SpecularMapFile = specularMapFile;
            this.Specular = specular;
            this.SpecularLoopNum = specularLoopNum;
            this.OcclusionMapFrom = occlusionMapFrom;
            this.OcclusionMapType = occlusionMapType;
            this.OcclusionMapUVFlip = occlusionMapUVFlip;
            this.OcclusionMapSwizzle = occlusionMapSwizzle;
            this.OcclusionMapApplyScale = occlusionMapApplyScale;
            this.OcclusionMapFile = occlusionMapFile;
            this.Occlusion = occlusion;
            this.OcclusionLoopNum = occlusionLoopNum;
            this.ParallaxMapFrom = parallaxMapFrom;
            this.ParallaxMapType = parallaxMapType;
            this.ParallaxMapUVFlip = parallaxMapUVFlip;
            this.ParallaxMapSwizzle = parallaxMapSwizzle;
            this.ParallaxMapFile = parallaxMapFile;
            this.Parallax = parallax;
            this.ParallaxLoopNum = parallaxLoopNum;
            this.EmissiveEnable = emissiveEnable;
            this.EmissiveMapFrom = emissiveMapFrom;
            this.EmissiveMapUVFlip = emissiveMapUVFlip;
            this.EmissiveMapApplyScale = emissiveMapApplyScale;
            this.EmissiveMapApplyMorphColor = emissiveMapApplyMorphColor;
            this.EmissiveMapApplyMorphIntensity = emissiveMapApplyMorphIntensity;
            this.EmissiveMapApplyBlink = emissiveMapApplyBlink;
            this.EmissiveMapFile = emissiveMapFile;
            this.Emissive = emissive;
            this.EmissiveBlink = emissiveBlink;
            this.emissiveIntensity = emissiveIntensity;
            this.EmissiveLoopNum = emissiveLoopNum;
            this.CustomEnable = customEnable;
            this.CustomAMapFrom = customAMapFrom;
            this.CustomAMapUVFlip = customAMapUVFlip;
            this.CustomAMapColorFlip = customAMapColorFlip;
            this.CustomAMapSwizzle = customAMapSwizzle;
            this.customAMapApplyScale = customAMapApplyScale;
            this.CustomAMapFile = customAMapFile;
            this.CustomA = customA;
            this.CustomALoopNum = customALoopNum;
            this.CustomBMapFrom = customBMapFrom;
            this.CustomBMapUVFlip = customBMapUVFlip;
            this.CustomBMapColorFlip = customBMapColorFlip;
            this.CustomBMapApplyScale = customBMapApplyScale;
            this.CustomBMapFile = customBMapFile;
            this.CustomB = customB;
            this.CustomBLoopNum = customBLoopNum;
            this.includeFxsub = includeFxsub;
            this.FilePath = filePath;
        }



        /*
         * Fetches a .fx ray-mmd material file
         */
        public void FetchMaterial()
        {
            string[] materialContents = File.ReadAllLines(this.FilePath);

            foreach (string line in materialContents)
            {
                AssignMaterialValues(line);
            }
        }


        /*
         * Assigns values to RayMaterial object from fetched file
         */
        private void AssignMaterialValues(string line)
        {
            //Under construction
            int integerFetchedValue;
            float floatFetechedValue;
            string stringFetchedValue;
            switch (line)
            {
                case string a when a.Contains("ALBEDO_MAP_FROM"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.AlbedoMapFrom = integerFetchedValue > -1 ? integerFetchedValue : this.AlbedoMapFrom;
                    break;
                case string a when a.Contains("ALBEDO_MAP_UV_FLIP"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.AlbedoMapUVFlip = integerFetchedValue > -1 ? integerFetchedValue : this.AlbedoMapUVFlip;
                    break;
                case string a when a.Contains("ALBEDO_MAP_APPLY_SCALE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.AlbedoMapApplyScale = integerFetchedValue > -1 ? integerFetchedValue : this.AlbedoMapApplyScale;
                    break;
                case string a when a.Contains("ALBEDO_MAP_APPLY_DIFFUSE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.AlbedoMapApplyDiffuse = integerFetchedValue > -1 ? integerFetchedValue : this.AlbedoMapApplyDiffuse;
                    break;
                case string a when a.Contains("ALBEDO_MAP_APPLY_MORPH_COLOR"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.AlbedoMapApplyMorphColor = integerFetchedValue > -1 ? integerFetchedValue : this.AlbedoMapApplyMorphColor;
                    break;
                case string a when a.Contains("ALBEDO_MAP_FILE"):
                    stringFetchedValue = Util.FetchSubStringBetween(line, '"');
                    this.AlbedoMapFile = !stringFetchedValue.Equals("") ? stringFetchedValue : this.AlbedoMapFile;
                    break;
                case string a when a.Contains("albedo "):
                    stringFetchedValue = Util.FetchSubStringBetween(line, '=', true, 1);
                    this.Albedo = !stringFetchedValue.Equals("") ? stringFetchedValue : this.Albedo;
                    break;
                case string a when a.Contains("albedoMapLoopNum"):
                    stringFetchedValue = Util.FetchMapLoop(line, '=');
                    this.AlbedoLoopNum = !stringFetchedValue.Equals("") ? stringFetchedValue : this.AlbedoLoopNum;
                    break;
                case string a when a.Contains("ALBEDO_SUB_ENABLE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.AlbedoSubEnable = integerFetchedValue > -1 ? integerFetchedValue : this.AlbedoSubEnable;
                    break;
                case string a when a.Contains("ALBEDO_SUB_MAP_FROM"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.AlbedoSubMapFrom = integerFetchedValue > -1 ? integerFetchedValue : this.AlbedoSubMapFrom;
                    break;
                case string a when a.Contains("ALBEDO_SUB_MAP_UV_FLIP"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.AlbedoSubMapUVFlip = integerFetchedValue > -1 ? integerFetchedValue : this.AlbedoSubMapUVFlip;
                    break;
                case string a when a.Contains("ALBEDO_SUB_MAP_APPLY_SCALE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.AlbedoSubMapApplyScale = integerFetchedValue > -1 ? integerFetchedValue : this.AlbedoSubMapApplyScale;
                    break;
                case string a when a.Contains("ALBEDO_SUB_MAP_FILE"):
                    stringFetchedValue = Util.FetchSubStringBetween(line, '"');
                    this.AlbedoSubMapFile = !stringFetchedValue.Equals("") ? stringFetchedValue : this.AlbedoSubMapFile;
                    break;
                case string a when a.Contains("albedoSub "):
                    stringFetchedValue = Util.FetchSubStringBetween(line, '=', true, 1);
                    this.AlbedoSub = !stringFetchedValue.Equals("") ? stringFetchedValue : this.AlbedoSub;
                    break;
                case string a when a.Contains("albedoSubMapLoopNum"):
                    stringFetchedValue = Util.FetchMapLoop(line, '=');
                    this.AlbedoSubLoopNum = !stringFetchedValue.Equals("") ? stringFetchedValue : this.AlbedoSubLoopNum;
                    break;
                case string a when a.Contains("ALPHA_MAP_FROM"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.AlphaMapFrom = integerFetchedValue > -1 ? integerFetchedValue : this.AlphaMapFrom;
                    break;
                case string a when a.Contains("ALPHA_MAP_UV_FLIP"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.AlphaMapUVFlip = integerFetchedValue > -1 ? integerFetchedValue : this.AlphaMapUVFlip;
                    break;
                case string a when a.Contains("ALPHA_MAP_SWIZZLE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.AlphaMapSwizzle = integerFetchedValue > -1 ? integerFetchedValue : this.AlphaMapSwizzle;
                    break;
                case string a when a.Contains("ALPHA_MAP_FILE"):
                    stringFetchedValue = Util.FetchSubStringBetween(line, '"');
                    this.AlphaMapFile = !stringFetchedValue.Equals("") ? stringFetchedValue : this.AlphaMapFile;
                    break;
                case string a when a.Contains("alpha ="):
                    floatFetechedValue = Util.FetchFloatNumber(line);
                    this.Alpha = floatFetechedValue;
                    break;
                case string a when a.Contains("alphaMapLoopNum"):
                    stringFetchedValue = Util.FetchMapLoop(line, '=');
                    this.AlphaLoopNum = !stringFetchedValue.Equals("") ? stringFetchedValue : this.AlphaLoopNum;
                    break;
                case string a when a.Contains("NORMAL_MAP_FROM"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.NormalMapFrom = integerFetchedValue > -1 ? integerFetchedValue : this.NormalMapFrom;
                    break;
                case string a when a.Contains("NORMAL_MAP_TYPE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.NormalMapType = integerFetchedValue > -1 ? integerFetchedValue : this.NormalMapType;
                    break;
                case string a when a.Contains("NORMAL_MAP_UV_FLIP"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.NormalMapUVFlip = integerFetchedValue > -1 ? integerFetchedValue : this.NormalMapUVFlip;
                    break;
                case string a when a.Contains("NORMAL_MAP_FILE"):
                    stringFetchedValue = Util.FetchSubStringBetween(line, '"');
                    this.NormalMapFile = !stringFetchedValue.Equals("") ? stringFetchedValue : this.NormalMapFile;
                    break;
                case string a when a.Contains("normalMapScale ="):
                    floatFetechedValue = Util.FetchFloatNumber(line);
                    this.Normal = floatFetechedValue;
                    break;
                case string a when a.Contains("normalMapLoopNum"):
                    stringFetchedValue = Util.FetchMapLoop(line, '=');
                    this.NormalLoopNum = !stringFetchedValue.Equals("") ? stringFetchedValue : this.NormalLoopNum;
                    break;
                case string a when a.Contains("NORMAL_SUB_MAP_FROM"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.NormalSubMapFrom = integerFetchedValue > -1 ? integerFetchedValue : this.NormalSubMapFrom;
                    break;
                case string a when a.Contains("NORMAL_SUB_MAP_TYPE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.NormalSubMapType = integerFetchedValue > -1 ? integerFetchedValue : this.NormalSubMapType;
                    break;
                case string a when a.Contains("NORMAL_SUB_MAP_UV_FLIP"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.NormalSubMapUVFlip = integerFetchedValue > -1 ? integerFetchedValue : this.NormalSubMapUVFlip;
                    break;
                case string a when a.Contains("NORMAL_SUB_MAP_FILE"):
                    stringFetchedValue = Util.FetchSubStringBetween(line, '"');
                    this.NormalSubMapFile = !stringFetchedValue.Equals("") ? stringFetchedValue : this.NormalSubMapFile;
                    break;
                case string a when a.Contains("normalSubMapScale ="):
                    floatFetechedValue = Util.FetchFloatNumber(line);
                    this.NormalSub = floatFetechedValue;
                    break;
                case string a when a.Contains("normalSubMapLoopNum"):
                    stringFetchedValue = Util.FetchMapLoop(line, '=');
                    this.NormalSubLoopNum = !stringFetchedValue.Equals("") ? stringFetchedValue : this.NormalSubLoopNum;
                    break;
                case string a when a.Contains("SMOOTHNESS_MAP_FROM"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.SmoothnessMapFrom = integerFetchedValue > -1 ? integerFetchedValue : this.SmoothnessMapFrom;
                    break;
                case string a when a.Contains("SMOOTHNESS_MAP_TYPE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.SmoothnessMapType = integerFetchedValue > -1 ? integerFetchedValue : this.SmoothnessMapType;
                    break;
                case string a when a.Contains("SMOOTHNESS_MAP_UV_FLIP"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.SmoothnessMapUVFlip = integerFetchedValue > -1 ? integerFetchedValue : this.SmoothnessMapUVFlip;
                    break;
                case string a when a.Contains("SMOOTHNESS_MAP_SWIZZLE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.SmoothnessMapSwizzle = integerFetchedValue > -1 ? integerFetchedValue : this.SmoothnessMapSwizzle;
                    break;
                case string a when a.Contains("SMOOTHNESS_MAP_APPLY_SCALE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.SmoothnessMapApplyScale = integerFetchedValue > -1 ? integerFetchedValue : this.SmoothnessMapApplyScale;
                    break;
                case string a when a.Contains("SMOOTHNESS_MAP_FILE"):
                    stringFetchedValue = Util.FetchSubStringBetween(line, '"');
                    this.SmoothnessMapFile = !stringFetchedValue.Equals("") ? stringFetchedValue : this.SmoothnessMapFile;
                    break;
                case string a when a.Contains("smoothness ="):
                    floatFetechedValue = Util.FetchFloatNumber(line);
                    this.Smoothness = floatFetechedValue;
                    break;
                case string a when a.Contains("smoothnessMapLoopNum"):
                    stringFetchedValue = Util.FetchMapLoop(line, '=');
                    this.SmoothnessLoopNum = !stringFetchedValue.Equals("") ? stringFetchedValue : this.SmoothnessLoopNum;
                    break;
                case string a when a.Contains("METALNESS_MAP_FROM"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.MetalnessMapFrom = integerFetchedValue > -1 ? integerFetchedValue : this.MetalnessMapFrom;
                    break;
                case string a when a.Contains("METALNESS_MAP_UV_FLIP"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.MetalnessMapUVFlip = integerFetchedValue > -1 ? integerFetchedValue : this.MetalnessMapUVFlip;
                    break;
                case string a when a.Contains("METALNESS_MAP_SWIZZLE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.MetalnessMapSwizzle = integerFetchedValue > -1 ? integerFetchedValue : this.MetalnessMapSwizzle;
                    break;
                case string a when a.Contains("METALNESS_MAP_APPLY_SCALE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.MetalnessMapApplyScale = integerFetchedValue > -1 ? integerFetchedValue : this.MetalnessMapApplyScale;
                    break;
                case string a when a.Contains("METALNESS_MAP_FILE"):
                    stringFetchedValue = Util.FetchSubStringBetween(line, '"');
                    this.MetalnessMapFile = !stringFetchedValue.Equals("") ? stringFetchedValue : this.MetalnessMapFile;
                    break;
                case string a when a.Contains("metalness ="):
                    floatFetechedValue = Util.FetchFloatNumber(line);
                    this.Metalness = floatFetechedValue;
                    break;
                case string a when a.Contains("metalnessMapLoopNum"):
                    stringFetchedValue = Util.FetchMapLoop(line, '=');
                    this.MetalnessLoopNum = !stringFetchedValue.Equals("") ? stringFetchedValue : this.MetalnessLoopNum;
                    break;
                case string a when a.Contains("SPECULAR_MAP_FROM"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.SpecularMapFrom = integerFetchedValue > -1 ? integerFetchedValue : this.SpecularMapFrom;
                    break;
                case string a when a.Contains("SPECULAR_MAP_TYPE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.SpecularMapType = integerFetchedValue > -1 ? integerFetchedValue : this.SpecularMapType;
                    break;
                case string a when a.Contains("SPECULAR_MAP_UV_FLIP"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.SpecularMapUVFlip = integerFetchedValue > -1 ? integerFetchedValue : this.SpecularMapUVFlip;
                    break;
                case string a when a.Contains("SPECULAR_MAP_SWIZZLE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.SpecularMapSwizzle = integerFetchedValue > -1 ? integerFetchedValue : this.SpecularMapSwizzle;
                    break;
                case string a when a.Contains("SPECULAR_MAP_APPLY_SCALE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.SpecularMapApplyScale = integerFetchedValue > -1 ? integerFetchedValue : this.SpecularMapApplyScale;
                    break;
                case string a when a.Contains("SPECULAR_MAP_FILE"):
                    stringFetchedValue = Util.FetchSubStringBetween(line, '"');
                    this.SpecularMapFile = !stringFetchedValue.Equals("") ? stringFetchedValue : this.SpecularMapFile;
                    break;
                case string a when a.Contains("specular ="):
                    stringFetchedValue = Util.FetchSubStringBetween(line, '=', true, 1);
                    this.Specular = !stringFetchedValue.Equals("") ? stringFetchedValue : this.Specular;
                    break;
                case string a when a.Contains("specularMapLoopNum"):
                    stringFetchedValue = Util.FetchMapLoop(line, '=');
                    this.SpecularLoopNum = !stringFetchedValue.Equals("") ? stringFetchedValue : this.SpecularLoopNum;
                    break;
                case string a when a.Contains("OCCLUSION_MAP_FROM"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.OcclusionMapFrom = integerFetchedValue > -1 ? integerFetchedValue : this.OcclusionMapFrom;
                    break;
                case string a when a.Contains("OCCLUSION_MAP_TYPE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.OcclusionMapType = integerFetchedValue > -1 ? integerFetchedValue : this.OcclusionMapType;
                    break;
                case string a when a.Contains("OCCLUSION_MAP_UV_FLIP"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.OcclusionMapUVFlip = integerFetchedValue > -1 ? integerFetchedValue : this.OcclusionMapUVFlip;
                    break;
                case string a when a.Contains("OCCLUSION_MAP_SWIZZLE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.OcclusionMapSwizzle = integerFetchedValue > -1 ? integerFetchedValue : this.OcclusionMapSwizzle;
                    break;
                case string a when a.Contains("OCCLUSION_MAP_APPLY_SCALE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.OcclusionMapApplyScale = integerFetchedValue > -1 ? integerFetchedValue : this.OcclusionMapApplyScale;
                    break;
                case string a when a.Contains("OCCLUSION_MAP_FILE"):
                    stringFetchedValue = Util.FetchSubStringBetween(line, '"');
                    this.OcclusionMapFile = !stringFetchedValue.Equals("") ? stringFetchedValue : this.OcclusionMapFile;
                    break;
                case string a when a.Contains("occlusion ="):
                    floatFetechedValue = Util.FetchFloatNumber(line);
                    this.Occlusion = floatFetechedValue;
                    break;
                case string a when a.Contains("occlusionMapLoopNum"):
                    stringFetchedValue = Util.FetchMapLoop(line, '=');
                    this.OcclusionLoopNum = !stringFetchedValue.Equals("") ? stringFetchedValue : this.OcclusionLoopNum;
                    break;
                case string a when a.Contains("PARALLAX_MAP_FROM"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.ParallaxMapFrom = integerFetchedValue > -1 ? integerFetchedValue : this.ParallaxMapFrom;
                    break;
                case string a when a.Contains("PARALLAX_MAP_TYPE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.ParallaxMapType = integerFetchedValue > -1 ? integerFetchedValue : this.ParallaxMapType;
                    break;
                case string a when a.Contains("PARALLAX_MAP_UV_FLIP"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.ParallaxMapUVFlip = integerFetchedValue > -1 ? integerFetchedValue : this.ParallaxMapUVFlip;
                    break;
                case string a when a.Contains("PARALLAX_MAP_SWIZZLE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.ParallaxMapSwizzle = integerFetchedValue > -1 ? integerFetchedValue : this.ParallaxMapSwizzle;
                    break;
                case string a when a.Contains("PARALLAX_MAP_FILE"):
                    stringFetchedValue = Util.FetchSubStringBetween(line, '"');
                    this.ParallaxMapFile = !stringFetchedValue.Equals("") ? stringFetchedValue : this.ParallaxMapFile;
                    break;
                case string a when a.Contains("parallaxMapScale ="):
                    floatFetechedValue = Util.FetchFloatNumber(line);
                    this.Parallax = floatFetechedValue;
                    break;
                case string a when a.Contains("parallaxMapLoopNum"):
                    stringFetchedValue = Util.FetchMapLoop(line, '=');
                    this.ParallaxLoopNum = !stringFetchedValue.Equals("") ? stringFetchedValue : this.ParallaxLoopNum;
                    break;
                case string a when a.Contains("EMISSIVE_ENABLE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.ParallaxMapFrom = integerFetchedValue > -1 ? integerFetchedValue : this.ParallaxMapFrom;
                    break;
                case string a when a.Contains("EMISSIVE_MAP_FROM"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.EmissiveMapFrom = integerFetchedValue > -1 ? integerFetchedValue : this.EmissiveMapFrom;
                    break;
                case string a when a.Contains("EMISSIVE_MAP_UV_FLIP"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.EmissiveMapUVFlip = integerFetchedValue > -1 ? integerFetchedValue : this.EmissiveMapUVFlip;
                    break;
                case string a when a.Contains("EMISSIVE_MAP_APPLY_SCALE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.EmissiveMapApplyScale = integerFetchedValue > -1 ? integerFetchedValue : this.EmissiveMapApplyScale;
                    break;
                case string a when a.Contains("EMISSIVE_MAP_APPLY_MORPH_COLOR"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.EmissiveMapApplyMorphColor = integerFetchedValue > -1 ? integerFetchedValue : this.EmissiveMapApplyMorphColor;
                    break;
                case string a when a.Contains("EMISSIVE_MAP_APPLY_MORPH_INTENSITY"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.EmissiveMapApplyMorphIntensity = integerFetchedValue > -1 ? integerFetchedValue : this.EmissiveMapApplyMorphIntensity;
                    break;
                case string a when a.Contains("EMISSIVE_MAP_APPLY_BLINK"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.EmissiveMapApplyBlink = integerFetchedValue > -1 ? integerFetchedValue : this.EmissiveMapApplyBlink;
                    break;
                case string a when a.Contains("EMISSIVE_MAP_FILE"):
                    stringFetchedValue = Util.FetchSubStringBetween(line, '"');
                    this.EmissiveMapFile = !stringFetchedValue.Equals("") ? stringFetchedValue : this.EmissiveMapFile;
                    break;
                case string a when a.Contains("emissive ="):
                    stringFetchedValue = Util.FetchSubStringBetween(line, '=', true, 1);
                    this.Emissive = !stringFetchedValue.Equals("") ? stringFetchedValue : this.Emissive;
                    break;
                case string a when a.Contains("emissiveBlink"):
                    floatFetechedValue = Util.FetchFloatNumber(line);
                    this.EmissiveBlink = floatFetechedValue;
                    break;
                case string a when a.Contains("emissiveIntensity"):
                    floatFetechedValue = Util.FetchFloatNumber(line);
                    this.EmissiveIntensity = floatFetechedValue;
                    break;
                case string a when a.Contains("emissiveMapLoopNum"):
                    stringFetchedValue = Util.FetchMapLoop(line, '=');
                    this.EmissiveLoopNum = !stringFetchedValue.Equals("") ? stringFetchedValue : this.EmissiveLoopNum;
                    break;
                case string a when a.Contains("CUSTOM_ENABLE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.CustomEnable = integerFetchedValue > -1 ? integerFetchedValue : this.CustomEnable;
                    break;
                case string a when a.Contains("CUSTOM_A_MAP_FROM"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.CustomAMapFrom = integerFetchedValue > -1 ? integerFetchedValue : this.CustomAMapFrom;
                    break;
                case string a when a.Contains("CUSTOM_A_MAP_UV_FLIP"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.CustomAMapUVFlip = integerFetchedValue > -1 ? integerFetchedValue : this.CustomAMapUVFlip;
                    break;
                case string a when a.Contains("CUSTOM_A_MAP_COLOR_FLIP"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.CustomAMapColorFlip = integerFetchedValue > -1 ? integerFetchedValue : this.CustomAMapColorFlip;
                    break;
                case string a when a.Contains("CUSTOM_A_MAP_SWIZZLE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.CustomAMapSwizzle = integerFetchedValue > -1 ? integerFetchedValue : this.CustomAMapSwizzle;
                    break;
                case string a when a.Contains("CUSTOM_A_MAP_APPLY_SCALE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.CustomAMapApplyScale = integerFetchedValue > -1 ? integerFetchedValue : this.CustomAMapApplyScale;
                    break;
                case string a when a.Contains("CUSTOM_A_MAP_FILE"):
                    stringFetchedValue = Util.FetchSubStringBetween(line, '"');
                    this.CustomAMapFile = !stringFetchedValue.Equals("") ? stringFetchedValue : this.CustomAMapFile;
                    break;
                case string a when a.Contains("customA "):
                    floatFetechedValue = Util.FetchFloatNumber(line);
                    this.CustomA = floatFetechedValue;
                    break;
                case string a when a.Contains("customAMapLoopNum"):
                    stringFetchedValue = Util.FetchMapLoop(line, '=');
                    this.CustomALoopNum = !stringFetchedValue.Equals("") ? stringFetchedValue : this.CustomALoopNum;
                    break;
                case string a when a.Contains("CUSTOM_B_MAP_FROM"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.CustomBMapFrom = integerFetchedValue > -1 ? integerFetchedValue : this.CustomBMapFrom;
                    break;
                case string a when a.Contains("CUSTOM_B_MAP_UV_FLIP"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.CustomBMapUVFlip = integerFetchedValue > -1 ? integerFetchedValue : this.CustomBMapUVFlip;
                    break;
                case string a when a.Contains("CUSTOM_B_MAP_COLOR_FLIP"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.CustomBMapColorFlip = integerFetchedValue > -1 ? integerFetchedValue : this.CustomBMapColorFlip;
                    break;
                case string a when a.Contains("CUSTOM_B_MAP_APPLY_SCALE"):
                    integerFetchedValue = Util.FetchNumberParamValue(line);
                    this.CustomBMapApplyScale = integerFetchedValue > -1 ? integerFetchedValue : this.CustomBMapApplyScale;
                    break;
                case string a when a.Contains("CUSTOM_B_MAP_FILE"):
                    stringFetchedValue = Util.FetchSubStringBetween(line, '"');
                    this.CustomBMapFile = !stringFetchedValue.Equals("") ? stringFetchedValue : this.CustomBMapFile;
                    break;
                case string a when a.Contains("customB ="):
                    stringFetchedValue = Util.FetchSubStringBetween(line, '=', true, 1);
                    this.CustomB = !stringFetchedValue.Equals("") ? stringFetchedValue : this.CustomB;
                    break;
                case string a when a.Contains("customBMapLoopNum"):
                    stringFetchedValue = Util.FetchMapLoop(line, '=');
                    this.CustomBLoopNum = !stringFetchedValue.Equals("") ? stringFetchedValue : this.CustomBLoopNum;
                    break;
                case string a when a.Contains("#include"):
                    stringFetchedValue = Util.FetchSubStringBetween(line, '"');
                    this.IncludeFxsub = !stringFetchedValue.Equals("") ? stringFetchedValue : this.IncludeFxsub;
                    break;

            }
        }

        /*
         * Function that saves a material file
         */
        public void Save()
        {
            File.WriteAllText(this.FilePath, this.ToString());
        }

        public override string ToString()
        {
            return "#define ALBEDO_MAP_FROM " + this.AlbedoMapFrom + "\r\n" +
                    "#define ALBEDO_MAP_UV_FLIP " + this.AlbedoMapUVFlip + "\r\n" +
                    "#define ALBEDO_MAP_APPLY_SCALE " + this.AlbedoMapApplyScale + "\r\n" +
                    "#define ALBEDO_MAP_APPLY_DIFFUSE " + this.AlbedoMapApplyDiffuse + "\r\n" +
                    "#define ALBEDO_MAP_APPLY_MORPH_COLOR " + this.AlbedoMapApplyMorphColor + "\r\n" +
                    "#define ALBEDO_MAP_FILE " + this.AlbedoMapFile + "\r\n" +
                    "\r\n" +
                    "const float3 albedo =" + this.Albedo + ";\r\n" +
                    "const float2 albedoMapLoopNum =" + this.AlbedoLoopNum + ";\r\n" +
                    "\r\n" +
                    "#define ALBEDO_SUB_ENABLE " + this.AlbedoSubEnable + "\r\n" +
                    "#define ALBEDO_SUB_MAP_FROM " + this.AlbedoSubMapFrom + "\r\n" +
                    "#define ALBEDO_SUB_MAP_UV_FLIP " + this.AlbedoSubMapUVFlip + "\r\n" +
                    "#define ALBEDO_SUB_MAP_APPLY_SCALE " + this.AlbedoSubMapApplyScale + "\r\n" +
                    "#define ALBEDO_SUB_MAP_FILE " + this.AlbedoSubMapFile + "\r\n" +
                    "\r\n" +
                    "const float3 albedoSub =" + this.AlbedoSub + ";\r\n" +
                    "const float2 albedoSubMapLoopNum =" + this.AlbedoSubLoopNum + ";\r\n" +
                    "\r\n" +
                    "#define ALPHA_MAP_FROM " + this.AlphaMapFrom + "\r\n" +
                    "#define ALPHA_MAP_UV_FLIP " + this.AlphaMapUVFlip + "\r\n" +
                    "#define ALPHA_MAP_SWIZZLE " + this.AlphaMapSwizzle + "\r\n" +
                    "#define ALPHA_MAP_FILE " + this.AlphaMapFile + "\r\n" +
                    "\r\n" +
                    "const float alpha = " + this.Alpha.ToString("0.00") + ";\r\n" +
                    "const float alphaMapLoopNum =" + this.AlphaLoopNum + ";\r\n" +
                    "\r\n" +
                    "#define NORMAL_MAP_FROM " + this.NormalMapFrom + "\r\n" +
                    "#define NORMAL_MAP_TYPE " + this.NormalMapType + "\r\n" +
                    "#define NORMAL_MAP_UV_FLIP " + this.NormalMapUVFlip + "\r\n" +
                    "#define NORMAL_MAP_FILE " + this.NormalMapFile + "\r\n" +
                    "\r\n" +
                    "const float normalMapScale = " + this.Normal.ToString("0.00") + ";\r\n" +
                    "const float normalMapLoopNum =" + this.NormalLoopNum + ";\r\n" +
                    "\r\n" +
                    "#define NORMAL_SUB_MAP_FROM " + this.NormalSubMapFrom + "\r\n" +
                    "#define NORMAL_SUB_MAP_TYPE " + this.NormalSubMapType + "\r\n" +
                    "#define NORMAL_SUB_MAP_UV_FLIP " + this.NormalSubMapUVFlip + "\r\n" +
                    "#define NORMAL_SUB_MAP_FILE " + this.NormalSubMapFile + "\r\n" +
                    "\r\n" +
                    "const float normalSubMapScale = " + this.NormalSub.ToString("0.00") + ";\r\n" +
                    "const float normalSubMapLoopNum =" + this.NormalSubLoopNum + ";\r\n" +
                    "\r\n" +
                    "#define SMOOTHNESS_MAP_FROM " + this.SmoothnessMapFrom + "\r\n" +
                    "#define SMOOTHNESS_MAP_TYPE " + this.SmoothnessMapType + "\r\n" +
                    "#define SMOOTHNESS_MAP_UV_FLIP " + this.SmoothnessMapUVFlip + "\r\n" +
                    "#define SMOOTHNESS_MAP_SWIZZLE " + this.SmoothnessMapSwizzle + "\r\n" +
                    "#define SMOOTHNESS_MAP_APPLY_SCALE " + this.SmoothnessMapApplyScale + "\r\n" +
                    "#define SMOOTHNESS_MAP_FILE " + this.SmoothnessMapFile + "\r\n" +
                    "\r\n" +
                    "const float smoothness = " + this.Smoothness.ToString("0.00") + ";\r\n" +
                    "const float smoothnessMapLoopNum =" + this.SmoothnessLoopNum + ";\r\n" +
                    "\r\n" +
                    "#define METALNESS_MAP_FROM " + this.MetalnessMapFrom + "\r\n" +
                    "#define METALNESS_MAP_UV_FLIP " + this.MetalnessMapUVFlip + "\r\n" +
                    "#define METALNESS_MAP_SWIZZLE " + this.MetalnessMapSwizzle + "\r\n" +
                    "#define METALNESS_MAP_APPLY_SCALE " + this.MetalnessMapApplyScale + "\r\n" +
                    "#define METALNESS_MAP_FILE " + this.MetalnessMapFile + "\r\n" +
                    "\r\n" +
                    "const float metalness = " + this.Metalness.ToString("0.00") + ";\r\n" +
                    "const float metalnessMapLoopNum =" + this.MetalnessLoopNum + ";\r\n" +
                    "\r\n" +
                    "#define SPECULAR_MAP_FROM " + this.SpecularMapFrom + "\r\n" +
                    "#define SPECULAR_MAP_TYPE " + this.SpecularMapType + "\r\n" +
                    "#define SPECULAR_MAP_UV_FLIP " + this.SpecularMapUVFlip + "\r\n" +
                    "#define SPECULAR_MAP_SWIZZLE " + this.SpecularMapSwizzle + "\r\n" +
                    "#define SPECULAR_MAP_APPLY_SCALE " + this.SpecularMapApplyScale + "\r\n" +
                    "#define SPECULAR_MAP_FILE " + this.SpecularMapFile + "\r\n" +
                    "\r\n" +
                    "const float3 specular = " + this.Specular + ";\r\n" +
                    "const float2 specularMapLoopNum =" + this.SpecularLoopNum + ";\r\n" +
                    "\r\n" +
                    "#define OCCLUSION_MAP_FROM " + this.OcclusionMapFrom + "\r\n" +
                    "#define OCCLUSION_MAP_TYPE " + this.OcclusionMapType + "\r\n" +
                    "#define OCCLUSION_MAP_UV_FLIP " + this.OcclusionMapUVFlip + "\r\n" +
                    "#define OCCLUSION_MAP_SWIZZLE " + this.OcclusionMapSwizzle + "\r\n" +
                    "#define OCCLUSION_MAP_APPLY_SCALE " + this.OcclusionMapApplyScale + "\r\n" +
                    "#define OCCLUSION_MAP_FILE " + this.OcclusionMapFile + "\r\n" +
                    "\r\n" +
                    "const float occlusion = " + this.Occlusion.ToString("0.00") + ";\r\n" +
                    "const float occlusionMapLoopNum =" + this.OcclusionLoopNum + ";\r\n" +
                    "\r\n" +
                    "#define PARALLAX_MAP_FROM " + this.ParallaxMapFrom + "\r\n" +
                    "#define PARALLAX_MAP_TYPE " + this.ParallaxMapType + "\r\n" +
                    "#define PARALLAX_MAP_UV_FLIP " + this.ParallaxMapUVFlip + "\r\n" +
                    "#define PARALLAX_MAP_SWIZZLE " + this.ParallaxMapSwizzle + "\r\n" +
                    "#define PARALLAX_MAP_FILE " + this.ParallaxMapFile + "\r\n" +
                    "\r\n" +
                    "const float parallaxMapScale = " + this.Parallax.ToString("0.00") + ";\r\n" +
                    "const float parallaxMapLoopNum =" + this.ParallaxLoopNum + ";\r\n" +
                    "\r\n" +
                    "#define EMISSIVE_ENABLE " + this.EmissiveEnable + "\r\n" +
                    "#define EMISSIVE_MAP_FROM " + this.EmissiveMapFrom + "\r\n" +
                    "#define EMISSIVE_MAP_UV_FLIP " + this.EmissiveMapUVFlip + "\r\n" +
                    "#define EMISSIVE_MAP_APPLY_SCALE " + this.EmissiveMapApplyScale + "\r\n" +
                    "#define EMISSIVE_MAP_APPLY_MORPH_COLOR " + this.EmissiveMapApplyMorphColor + "\r\n" +
                    "#define EMISSIVE_MAP_APPLY_MORPH_INTENSITY " + this.EmissiveMapApplyMorphIntensity + "\r\n" +
                    "#define EMISSIVE_MAP_APPLY_BLINK " + this.EmissiveMapApplyBlink + "\r\n" +
                    "#define EMISSIVE_MAP_FILE " + this.EmissiveMapFile + "\r\n" +
                    "\r\n" +
                    "const float3 emissive = " + this.Emissive + ";\r\n" +
                    "const float3 emissiveBlink = " + this.EmissiveBlink.ToString("0.00") + ";\r\n" +
                    "const float  emissiveIntensity = " + this.EmissiveIntensity.ToString("0.00") + ";\r\n" +
                    "const float2 emissiveMapLoopNum =" + this.EmissiveLoopNum + ";\r\n" +
                    "\r\n" +
                    "#define CUSTOM_ENABLE " + this.CustomEnable + "\r\n" +
                    "\r\n" +
                    "#define CUSTOM_A_MAP_FROM " + this.CustomAMapFrom + "\r\n" +
                    "#define CUSTOM_A_MAP_UV_FLIP " + this.CustomAMapUVFlip + "\r\n" +
                    "#define CUSTOM_A_MAP_COLOR_FLIP " + this.CustomAMapColorFlip + "\r\n" +
                    "#define CUSTOM_A_MAP_SWIZZLE " + this.CustomAMapSwizzle + "\r\n" +
                    "#define CUSTOM_A_MAP_APPLY_SCALE " + this.CustomAMapApplyScale + "\r\n" +
                    "#define CUSTOM_A_MAP_FILE " + this.CustomAMapFile + "\r\n" +
                    "\r\n" +
                    "const float customA = " + this.CustomA.ToString("0.00") + ";\r\n" +
                    "const float2 customAMapLoopNum =" + this.CustomALoopNum + ";\r\n" +
                    "\r\n" +
                    "#define CUSTOM_B_MAP_FROM " + this.CustomBMapFrom + "\r\n" +
                    "#define CUSTOM_B_MAP_UV_FLIP " + this.CustomBMapUVFlip + "\r\n" +
                    "#define CUSTOM_B_MAP_COLOR_FLIP " + this.CustomBMapColorFlip + "\r\n" +
                    "#define CUSTOM_B_MAP_APPLY_SCALE " + this.CustomBMapApplyScale + "\r\n" +
                    "#define CUSTOM_B_MAP_FILE " + this.CustomBMapFile + "\r\n" +
                    "\r\n" +
                    "const float3 customB =" + this.CustomB + ";\r\n" +
                    "const float2 customBMapLoopNum =" + this.CustomBLoopNum + ";\r\n" +
                    "\r\n" +
                    "#include " + this.IncludeFxsub + "\r\n";
        }


    }
}
