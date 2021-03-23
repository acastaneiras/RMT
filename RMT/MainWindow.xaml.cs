using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using Microsoft.Win32;
using REghZyFramework.Themes;
using RMT.Model;
using RMT.View;
using Path = System.IO.Path;
namespace RMT
{
	/// <summary>
	/// Interaction logic for MainWindow.xaml
	/// </summary>
	public partial class MainWindow : Window
	{
		private const String APP_NAME = "Ray-mmd Material Tool v2.0";
		private enum MAP_MODES : int {LINEAR_COLOR =0, SRGB = 1, LINEAR_SRGB = 2 };
		private const String FILE_NOT_RECOGNIZED_MESSAGE = "File type not recognised.\nMake sure the file extension\nis correct.";
		private EMM project;
	
		private RayMaterial material;

		public bool initializationEnded = false;
		private String mapFilesPath = Directory.GetCurrentDirectory();
		private string imageFilters = "All Graphics Types|*.bmp;*.jpg;*.jpeg;*.png;*.tif;*.tiff"+ "BMP(*.bmp)|*.bmp|GIF(*.gif)|*.gif|JPG(*.jpg)|*.jpg;*.jpeg|PNG(*.png)|*.png|TIFF(*.tif)|*.tif;*.tiff|";

		//Handling ColorPicker changes with variables, since the event triggers the ColorChanged even when you're dragging your cursor
		private String albedoSelectedColor = null;
		private String albedoSubSelectedColor = null;

		public MainWindow()
		{
			RecoverThemeFromUserSettings();
			material = new RayMaterial();
			InitializeComponent();
			InitializeValues();
			initializationEnded = true;
			checkArgs(Environment.GetCommandLineArgs());
		}
	
		private void checkArgs(string[] args)
		{
			if (args.Length > 1) {
				switch (Path.GetExtension(args[1])) {
					case ".fx" :
						MessageBox.Show("material file");
						openMaterial(args[1]);
						break;
					case ".emm" :
						MessageBox.Show("project file");
						openProject(args[1]);
						break;
					default :
						MessageBox.Show(FILE_NOT_RECOGNIZED_MESSAGE);
						break;
				}
			}
		}

		/*
		 *  Restores theme setting from the user and changes the theme accordingly 
		 */
		private void RecoverThemeFromUserSettings()
        {
			uint themeUID = Properties.Settings.Default.Theme;
			switch (themeUID)
			{
				case 0:
					ThemesController.SetTheme(ThemesController.ThemeTypes.ColourfulLight);
					break;
				case 1:
					ThemesController.SetTheme(ThemesController.ThemeTypes.ColourfulDark);
					break;
			}
			
		}
		private void ChangeTheme(object sender, RoutedEventArgs e)
		{
            int themeUID = int.Parse(((MenuItem)sender).Uid);
			Properties.Settings.Default.Theme = (uint)themeUID;
			Properties.Settings.Default.Save();
			switch (themeUID)
			{
				case 0: 
					ThemesController.SetTheme(ThemesController.ThemeTypes.ColourfulLight);
					break;
				case 1: 
					ThemesController.SetTheme(ThemesController.ThemeTypes.ColourfulDark);
					break;
			}
			e.Handled = true;
		}
		private void InitializeValues()
		{
			//ALBEDO
			this.albedoMapFrom.SelectedIndex =this.material.AlbedoMapFrom;
			this.albedoMapUVFlip.SelectedIndex = this.material.AlbedoMapUVFlip;
			this.albedoMapApplyScale.SelectedIndex = this.material.AlbedoMapApplyScale;
			this.albedoMapApplyDiffuse.SelectedIndex = this.material.AlbedoMapApplyDiffuse;
			this.albedoMapApplyMorphColor.SelectedIndex = this.material.AlbedoMapApplyMorphColor;
			this.albedoMapFile.Content = this.material.AlbedoMapFile;
			this.albedo.Text = this.material.Albedo;
			this.albedoLoopNumX.Value= handleLoopNums(this.material.AlbedoLoopNum)[0];
			this.albedoLoopNumY.Value= handleLoopNums(this.material.AlbedoLoopNum)[1];
			this.albedoLoopNum.Text = assignLoopNum(this.albedoLoopNumX.Value, this.albedoLoopNumY.Value);
			ChangeMapScaleMode(this.material.Albedo, this.albedoMode);
			//ALBEDO SUB
			this.albedoSubEnable.SelectedIndex = this.material.AlbedoSubEnable;
			this.albedoSubMapFrom.SelectedIndex = this.material.AlbedoSubMapFrom;
			this.albedoSubMapUVFlip.SelectedIndex = this.material.AlbedoSubMapUVFlip;
			this.albedoSubMapApplyScale.SelectedIndex = this.material.AlbedoSubMapApplyScale;
			this.albedoSubMapFile.Content = this.material.AlbedoSubMapFile;
			this.albedoSub.Text = this.material.AlbedoSub;
			this.albedoSubLoopNumX.Value = handleLoopNums(this.material.AlbedoSubLoopNum)[0];
			this.albedoSubLoopNumY.Value = handleLoopNums(this.material.AlbedoSubLoopNum)[1];
			this.albedoSubLoopNum.Text = assignLoopNum(this.albedoSubLoopNumX.Value, this.albedoSubLoopNumY.Value);
			ChangeMapScaleMode(this.material.AlbedoSub, this.albedoSubMode);
			//ALPHA
			this.alphaMapFrom.SelectedIndex = this.material.AlphaMapFrom;
			this.alphaMapUVFlip.SelectedIndex = this.material.AlphaMapUVFlip;
			this.alphaMapSwizzle.SelectedIndex = this.material.AlphaMapSwizzle;
			this.alphaMapFile.Content = this.material.AlphaMapFile;
			this.alpha.Text = this.material.Alpha.ToString();
			this.alphaLoopNumX.Value = handleLoopNums(this.material.AlphaLoopNum)[0];
			this.alphaLoopNumY.Value = handleLoopNums(this.material.AlphaLoopNum)[1];
			this.alphaLoopNum.Text = assignLoopNum(this.alphaLoopNumX.Value, this.alphaLoopNumY.Value);
			//NORMAL
			this.normalMapFrom.SelectedIndex = this.material.NormalMapFrom;
			this.normalMapType.SelectedIndex = this.material.NormalMapType;
			this.normalMapUVFlip.SelectedIndex = this.material.NormalMapUVFlip;
			this.normalMapFile.Content = this.material.NormalMapFile;
			this.normal.Text = this.material.Normal.ToString();
			this.normalLoopNumX.Value = handleLoopNums(this.material.NormalLoopNum)[0];
			this.normalLoopNumY.Value = handleLoopNums(this.material.NormalLoopNum)[1];
			this.normalLoopNum.Text = assignLoopNum(this.normalLoopNumX.Value, this.normalLoopNumY.Value);
			//NORMAL SUB
			this.normalSubMapFrom.SelectedIndex = this.material.NormalSubMapFrom;
			this.normalSubMapType.SelectedIndex = this.material.NormalSubMapType;
			this.normalSubMapUVFlip.SelectedIndex = this.material.NormalSubMapUVFlip;
			this.normalSubMapFile.Content = this.material.NormalSubMapFile;
			this.normalSub.Text = this.material.NormalSub.ToString();
			this.normalSubLoopNumX.Value = handleLoopNums(this.material.NormalSubLoopNum)[0];
			this.normalSubLoopNumY.Value = handleLoopNums(this.material.NormalSubLoopNum)[1];
			this.normalSubLoopNum.Text = assignLoopNum(this.normalSubLoopNumX.Value, this.normalSubLoopNumY.Value);
			//SMOOTHNESS
			this.smoothnessMapFrom.SelectedIndex = this.material.SmoothnessMapFrom;
			this.smoothnessMapType.SelectedIndex = this.material.SmoothnessMapType;
			this.smoothnessMapUVFlip.SelectedIndex = this.material.SmoothnessMapUVFlip;
			this.smoothnessMapSwizzle.SelectedIndex = this.material.SmoothnessMapSwizzle;
			this.smoothnessMapApplyScale.SelectedIndex = this.material.SmoothnessMapApplyScale;
			this.smoothnessMapFile.Content = this.material.SmoothnessMapFile;
			this.smoothness.Text = this.material.Smoothness.ToString();
			this.smoothnessLoopNumX.Value = handleLoopNums(this.material.SmoothnessLoopNum)[0];
			this.smoothnessLoopNumY.Value = handleLoopNums(this.material.SmoothnessLoopNum)[1];
			this.smoothnessLoopNum.Text = assignLoopNum(this.smoothnessLoopNumX.Value, this.smoothnessLoopNumY.Value);
			//METALNESS
			this.metalnessMapFrom.SelectedIndex = this.material.MetalnessMapFrom;
			this.metalnessMapUVFlip.SelectedIndex = this.material.MetalnessMapUVFlip;
			this.metalnessMapSwizzle.SelectedIndex = this.material.MetalnessMapSwizzle;
			this.metalnessMapApplyScale.SelectedIndex = this.material.MetalnessMapApplyScale;
			this.metalnessMapFile.Content = this.material.MetalnessMapFile;
			this.metalness.Text = this.material.Metalness.ToString();
			this.metalnessLoopNumX.Value = handleLoopNums(this.material.MetalnessLoopNum)[0];
			this.metalnessLoopNumY.Value = handleLoopNums(this.material.MetalnessLoopNum)[1];
			this.metalnessLoopNum.Text = assignLoopNum(this.metalnessLoopNumX.Value, this.metalnessLoopNumY.Value);
			//OCCLUSION
			this.occlusionMapFrom.SelectedIndex = this.material.OcclusionMapFrom;
			this.occlusionMapType.SelectedIndex = this.material.OcclusionMapType;
			this.occlusionMapUVFlip.SelectedIndex = this.material.OcclusionMapUVFlip;
			this.occlusionMapSwizzle.SelectedIndex = this.material.OcclusionMapSwizzle;
			this.occlusionMapApplyScale.SelectedIndex = this.material.OcclusionMapApplyScale;
			this.occlusionMapFile.Content = this.material.OcclusionMapFile;
			this.occlusion.Text = this.material.Occlusion.ToString();
			this.occlusionLoopNumX.Value = handleLoopNums(this.material.OcclusionLoopNum)[0];
			this.occlusionLoopNumY.Value = handleLoopNums(this.material.OcclusionLoopNum)[1];
			this.occlusionLoopNum.Text = assignLoopNum(this.occlusionLoopNumX.Value, this.occlusionLoopNumY.Value);
		}

		/*
		 * Returns text with LoopNum format: float2(x,y) 
		 */
		private string assignLoopNum(double value1, double value2)
		{
			return "float2(" + Math.Round(value1, 2) + "," + Math.Round(value2, 2) + ")";
		}

		/*
		 * Splits the X & Y Values of the LoopNum param
		 * Eg.	arr[0] = X
		 * 		arr[1] = Y
		 */
		private double[] handleLoopNums(string line)
		{
			String substring = line.Substring(line.IndexOf("(") + 1);
			substring = substring.Remove(substring.Length - 1);
			
			return Array.ConvertAll(substring.Split(','), Double.Parse); ;
		}

		/*
		 * Used to know in which mode the xMapScale is 
		 * Case scenarios: 1.0 | float3(255, 0.0, 0.0) / 255.0 | pow(float3(r, g, b) / 255.0, 2.2)
		 */
		private void ChangeMapScaleMode(String mapScale, ComboBox comboBoxMode)
		{
			switch (mapScale)
			{
				case string a when !a.Contains("float"):
					comboBoxMode.SelectedIndex = (int)MAP_MODES.LINEAR_COLOR;
					break;
				case string a when a.Contains("pow"):
					comboBoxMode.SelectedIndex = (int)MAP_MODES.LINEAR_SRGB; 
					break;
				default:
					comboBoxMode.SelectedIndex = (int)MAP_MODES.SRGB;
					break;
			}
		}

		/*
		 * Function that gets called everytime that there's a change in the material
		 * bool write parameter is used to disable this write feature, mainly when values are initialized
		 */
		private void handleChanges()
		{
			if (initializationEnded && !this.material.FilePath.Equals("")) 
					this.material.Save();
		}

		/*
		 * Enables all the tabs 
		 */
		private void enablePanel()
		{
			if (!this.material.FilePath.Equals("") && TabPanel.IsEnabled == false)
				TabPanel.IsEnabled = true;
		}

		private void createNewMaterial()
		{
			material = new RayMaterial();

			Stream myStream;
			SaveFileDialog saveFileDialog1 = new SaveFileDialog();

			saveFileDialog1.Filter = "Material files (*.fx)|*.fx";
			saveFileDialog1.FileName = "New_Material";
			saveFileDialog1.AddExtension = true;
			saveFileDialog1.Title = "Save new material file";
			saveFileDialog1.RestoreDirectory = true;
			bool? result = saveFileDialog1.ShowDialog();

			if (result == true)
			{
				if ((myStream = saveFileDialog1.OpenFile()) != null)
				{
					material.FilePath = Path.GetFullPath(saveFileDialog1.FileName);
					myStream.Close();
					material.Save();
					InitializeValues();
					enablePanel();
					this.Title = APP_NAME + " | Editing " + this.GetFileName(material.FilePath);
				}
			}
		}
		/*
		 * Opens a material.fx file
		 * TODO: 
		 *		Check for errors
		 */
		private void openMaterial(string fileName) 
		{
			material = new RayMaterial();
			if (!fileName.Equals(""))
			{
				material.FilePath = System.IO.Path.GetFullPath(fileName);
				material.FetchMaterial();
				InitializeValues();
				enablePanel();
				this.Title = APP_NAME + " | Editing " + this.GetFileName(fileName);
			}
		}
		
		private void openProject(string fileName)
		{
			project = new EMM();
			if (!fileName.Equals("")) 
			{
				project.FilePath = System.IO.Path.GetFullPath(fileName);
				project.FetchEMM();
				MessageBox.Show("Project successfully loaded.\nThis currently means jack shit though.");
			}
		}

		private String handleOpenDialog(String filter = "Material files (*.fx)|*.fx", String title = "Select map file")
		{
			OpenFileDialog openFileDialog = new OpenFileDialog();

			openFileDialog.InitialDirectory = mapFilesPath;
			openFileDialog.Filter = filter;
			openFileDialog.Title = title;
			openFileDialog.AddExtension = true;
			openFileDialog.RestoreDirectory = true;
			bool? result = openFileDialog.ShowDialog(this);
			if (result == true)
			{
				//Get the path of specified file
				return openFileDialog.FileName;
			}
			return "";
		}

		//TODO: Allow negative numbers.
		//I tried to do it with a cool regex for like 2h and it worked everywhere except in here and I'm going insane so fuck this shit honestly not today
		private void handleTextInput(ref object sender, ref TextCompositionEventArgs e)
		{

			bool approvedDecimalPoint = false;
			if (e.Text == ".")
			{
				if (!((TextBox)sender).Text.Contains("."))
				{
					approvedDecimalPoint = true;
				}
			}

			if (!(char.IsDigit(e.Text, e.Text.Length - 1) || approvedDecimalPoint))
			{
				e.Handled = true;
			}
		}

		private String GetFileName(String filePath)
		{
			return filePath.Substring(filePath.LastIndexOf("\\")+1);
		}

		/*
		 * Resets the value of a loopNum type paramater and also sets its sliders to default (1.00f)
		 */
		private String resetLoopNum(TextBox loopNum, Slider loopX, Slider loopY)
        {
			loopNum.Text = assignLoopNum(1.00f, 1.00f);
			loopX.Value = 1.00f;
			loopY.Value = 1.00f;
			return loopNum.Text;
		}

		private String UpdateRGBColor(int selectedIndex, String colorHexValue)
		{
			String colorString = "";
			Color color = (Color)ColorConverter.ConvertFromString(colorHexValue);

			switch (selectedIndex)
			{
				case (int)MAP_MODES.SRGB:
					colorString = " float3(" + color.R + "," + color.G + "," + color.B + ")/255";
					break;
				case (int)MAP_MODES.LINEAR_SRGB:
					colorString = " pow(float3(" + color.R + "," + color.G + "," + color.B + ")/255, 2.2)";
					break;
			}
			return colorString;
		}

		/*
		 * Function that converts a String of type floatX(x,y,z) into an array of rgb like: byte[0] = x, byte[1] = y, byte[2] = z
		 * Returns a default color when there's no floatX value. (when we change from linear value to rgb)
		 */
		private byte[] fetchRGBFromString(string str)
		{
			if (str.Contains("float"))
			{
				str = str.Remove(0, str.LastIndexOf("(") + 1);
				int end = str.IndexOf(")");
				string commaRGBValues = str.Substring(0, end);
				String[] rgbValues = commaRGBValues.Split(',');
				return new byte[] { Convert.ToByte(Int32.Parse(rgbValues[0])), Convert.ToByte(Int32.Parse(rgbValues[1])), Convert.ToByte(Int32.Parse(rgbValues[2])) };
			}
			return new byte[] { 255, 255, 255 };
		}

		private void MaterialFileDrop(object sender, DragEventArgs e)
		{
			if (e.Data.GetDataPresent(DataFormats.FileDrop))
			{
				string[] files = (string[])e.Data.GetData(DataFormats.FileDrop);
				//Checking for proper extension file
				if (Path.GetExtension(files[0]).Equals(".fx"))
				{
					//Only reading the first file, we don't care about multiple files
					openMaterial(files[0]);
				}
				else
				{
					MessageBox.Show(FILE_NOT_RECOGNIZED_MESSAGE);
				}
			}
		}

		//UI Events

		private void newMaterial_Click(object sender, RoutedEventArgs e)
		{
			createNewMaterial();
		}
	
		private void editMaterial_Click(object sender, RoutedEventArgs e)
		{
			openMaterial(this.handleOpenDialog("Material files (*.fx)|*.fx", "Open material file"));
		}
	
		private void loadProject_Click(object sender, RoutedEventArgs e)
		{
			openProject(this.handleOpenDialog("MME project files (*.emm)|*.emm", "Open project file"));
		}
	
		private void exit_Click(object sender, RoutedEventArgs e)
		{
			Application.Current.Shutdown();
		}

		/*ALBEDO*/
		private void albedoMapFrom_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.AlbedoMapFrom = albedoMapFrom.SelectedIndex;
			handleChanges();
		}

		private void albedoMapUVFlip_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.AlbedoMapUVFlip = albedoMapUVFlip.SelectedIndex;
			handleChanges();
		}

		private void albedoMapApplyScale_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.AlbedoMapApplyScale = albedoMapApplyScale.SelectedIndex;
			handleChanges();
		}

		private void albedoMapApplyDiffuse_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.AlbedoMapApplyDiffuse = albedoMapApplyDiffuse.SelectedIndex;
			handleChanges();
		}

		private void albedoMapApplyMorphColor_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.AlbedoMapApplyMorphColor = albedoMapApplyMorphColor.SelectedIndex;
			handleChanges();
		}

		private void albedoMapFile_Click(object sender, RoutedEventArgs e)
		{
			String mapFile = handleOpenDialog(imageFilters);
			if (!mapFile.Equals(""))
			{
				this.material.AlbedoMapFile = Util.GetRelativePath(this.material.FilePath, mapFile);
				this.albedoMapFile.Content = this.material.AlbedoMapFile;
				handleChanges();
			}
		}

		private void albedoMode_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			if (this.albedoMode.SelectedIndex == (int)MAP_MODES.LINEAR_COLOR)
            {
				albedoLinearColor.Visibility = Visibility.Visible;
				albedoRGB.Visibility = Visibility.Hidden;
				albedo.Visibility = Visibility.Visible;
				if (this.albedo.Text.Contains("float"))
				{
					this.albedo.Text = "1.0";
				}
				this.material.Albedo = this.albedo.Text;
			} else {
				albedoLinearColor.Visibility = Visibility.Hidden;
				albedoRGB.Visibility = Visibility.Visible;
				albedo.Visibility = Visibility.Hidden;

				if (this.albedoSelectedColor != null)
				{
					this.material.Albedo = this.UpdateRGBColor(this.albedoMode.SelectedIndex, this.albedoSelectedColor);
				}
				else
				{
					byte[] rgbValues = this.fetchRGBFromString(this.material.Albedo);
					Color color = Color.FromRgb(rgbValues[0], rgbValues[1], rgbValues[2]);
					albedoRGB.SelectedColor = color;
				}
			}
			handleChanges();
		}

        private void albedo_PreviewTextInput(object sender, TextCompositionEventArgs e)
		{
			handleTextInput(ref sender, ref e);
		}


		private void albedo_KeyUp(object sender, KeyEventArgs e)
		{
			if (this.material.Albedo != this.albedo.Text)
			{
				this.material.Albedo = this.albedo.Text;
				handleChanges();
			}
		}

		private void albedoRGB_Closed(object sender, RoutedEventArgs e)
		{
			if ((albedoRGB.SelectedColor.HasValue) && (!albedoRGB.SelectedColor.ToString().Equals(this.albedoSelectedColor)))
			{
				this.albedoSelectedColor = albedoRGB.SelectedColor.Value.ToString();
				this.material.Albedo = this.UpdateRGBColor(this.albedoMode.SelectedIndex, this.albedoSelectedColor);
				handleChanges();
			}
		}

		private void albedoLoopNumLock_Checked(object sender, RoutedEventArgs e)
		{
			if (albedoLoopNumY.IsEnabled)
			{
				albedoLoopNumY.IsEnabled = false;
			}
			this.albedoLoopNumY.Value = this.albedoLoopNumX.Value;
			this.albedoLoopNum.Text = assignLoopNum(this.albedoLoopNumX.Value, this.albedoLoopNumY.Value);
			this.material.AlbedoLoopNum = this.albedoLoopNum.Text;
			handleChanges();
		}

		private void albedoLoopNumLock_Unchecked(object sender, RoutedEventArgs e)
		{
			if (!albedoLoopNumY.IsEnabled)
			{
				albedoLoopNumY.IsEnabled = true;
			}
			this.albedoLoopNumY.Value = this.albedoLoopNumX.Value;
			this.material.AlbedoLoopNum = this.albedoLoopNum.Text;
			handleChanges();
		}

		private void albedoLoopNumX_DragCompleted(object sender, System.Windows.Controls.Primitives.DragCompletedEventArgs e)
		{
			if (albedoLoopNumLock.IsChecked == true)
			{
				this.albedoLoopNumY.Value = this.albedoLoopNumX.Value;
			}
			this.albedoLoopNum.Text = assignLoopNum(this.albedoLoopNumX.Value, this.albedoLoopNumY.Value);
			this.material.AlbedoLoopNum = this.albedoLoopNum.Text;
			handleChanges();
		}

		private void albedoLoopNumY_DragCompleted(object sender, System.Windows.Controls.Primitives.DragCompletedEventArgs e)
		{
			if (albedoLoopNumLock.IsChecked == false)
			{
				this.albedoLoopNum.Text = assignLoopNum(this.albedoLoopNumX.Value, this.albedoLoopNumY.Value);
				this.material.AlbedoLoopNum = this.albedoLoopNum.Text;
			}
			handleChanges();
		}

		private void albedoLinearColor_DragCompleted(object sender, System.Windows.Controls.Primitives.DragCompletedEventArgs e)
		{
			this.material.Albedo = albedo.Text;
			handleChanges();
		}

		private void albedoLoopNumX_ValueChanged(object sender, RoutedPropertyChangedEventArgs<double> e)
		{
			if (albedoLoopNumLock.IsChecked == true)
			{
				this.albedoLoopNumY.Value = this.albedoLoopNumX.Value;
			}
			this.albedoLoopNum.Text = assignLoopNum(this.albedoLoopNumX.Value, this.albedoLoopNumY.Value);
		}

		private void albedoLoopNumY_ValueChanged(object sender, RoutedPropertyChangedEventArgs<double> e)
		{
			if (albedoLoopNumLock.IsChecked == false)
			{
				this.albedoLoopNum.Text = assignLoopNum(this.albedoLoopNumX.Value, this.albedoLoopNumY.Value);
			}
		}

		private void albedoLoopNumReset_Click(object sender, RoutedEventArgs e)
		{
			this.material.AlbedoLoopNum = resetLoopNum(this.albedoLoopNum, this.albedoLoopNumX, this.albedoLoopNumY);
			handleChanges();
		}

        private void albedoMapFromHelp(object sender, RoutedEventArgs e)
        {
			Help dialog = new Help("albedo");
			dialog.Owner = this;
			dialog.Show();
		}
		/*END ALBEDO*/

		/*ALBEDO SUB*/
		private void albedoSubEnable_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.AlbedoSubEnable = albedoSubEnable.SelectedIndex;
			handleChanges();
		}

        private void albedoSubMapFrom_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
			this.material.AlbedoSubMapFrom = albedoSubMapFrom.SelectedIndex;
			handleChanges();
		}

        private void albedoSubMapUVFlip_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
			this.material.AlbedoSubMapUVFlip = albedoSubMapUVFlip.SelectedIndex;
			handleChanges();
		}

        private void albedoSubMapApplyScale_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
			this.material.AlbedoSubMapApplyScale = albedoSubMapApplyScale.SelectedIndex;
			handleChanges();
		}

        private void albedoSubMapFileButton_Click(object sender, RoutedEventArgs e)
        {
			String mapFile = handleOpenDialog(imageFilters);
			if (!mapFile.Equals(""))
			{
				this.material.AlbedoSubMapFile = Util.GetRelativePath(this.material.FilePath, mapFile);
				this.albedoSubMapFile.Content = this.material.AlbedoSubMapFile;
				handleChanges();
			}
		}

        private void albedoSubLinearColor_DragCompleted(object sender, System.Windows.Controls.Primitives.DragCompletedEventArgs e)
        {
			this.material.AlbedoSub = albedoSub.Text;
			handleChanges();
		}

        private void albedoSubMode_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
			if (this.albedoSubMode.SelectedIndex == (int)MAP_MODES.LINEAR_COLOR)
			{
				albedoSubLinearColor.Visibility = Visibility.Visible;
				albedoSubRGB.Visibility = Visibility.Hidden;
				albedoSub.Visibility = Visibility.Visible;
				if (this.albedoSub.Text.Contains("float"))
				{
					this.albedoSub.Text = "1.0";
				}
				this.material.AlbedoSub = this.albedoSub.Text;
			} else {
				albedoSubLinearColor.Visibility = Visibility.Hidden;
				albedoSubRGB.Visibility = Visibility.Visible;
				albedoSub.Visibility = Visibility.Hidden;

				if (this.albedoSubSelectedColor != null)
				{
					this.material.AlbedoSub = this.UpdateRGBColor(this.albedoSubMode.SelectedIndex, this.albedoSubSelectedColor);
				}
				else
				{
					byte[] rgbValues = this.fetchRGBFromString(this.material.AlbedoSub);
					Color color = Color.FromRgb(rgbValues[0], rgbValues[1], rgbValues[2]);
					albedoSubRGB.SelectedColor = color;
				}
			}
			handleChanges();
		}

        private void albedoSub_PreviewTextInput(object sender, TextCompositionEventArgs e)
        {
			handleTextInput(ref sender, ref e);
		}

		private void albedoSub_KeyUp(object sender, KeyEventArgs e)
		{
			if (this.material.AlbedoSub != this.albedoSub.Text)
			{
				this.material.AlbedoSub = this.albedoSub.Text;
				handleChanges();
			}
		}

		private void albedoSubRGB_Closed(object sender, RoutedEventArgs e)
        {
			if ((albedoSubRGB.SelectedColor.HasValue) && (!albedoSubRGB.SelectedColor.ToString().Equals(this.albedoSubSelectedColor)))
			{
				this.albedoSubSelectedColor = albedoSubRGB.SelectedColor.Value.ToString();
				this.material.AlbedoSub = this.UpdateRGBColor(this.albedoSubMode.SelectedIndex, this.albedoSubSelectedColor);
				handleChanges();
			}
		}

		private void albedoSubLoopNumLock_Checked(object sender, RoutedEventArgs e)
        {
			if (albedoSubLoopNumY.IsEnabled)
			{
				albedoSubLoopNumY.IsEnabled = false;
			}
			this.albedoSubLoopNumY.Value = this.albedoSubLoopNumX.Value;
			this.albedoSubLoopNum.Text = assignLoopNum(this.albedoSubLoopNumX.Value, this.albedoSubLoopNumY.Value);
			this.material.AlbedoSubLoopNum = this.albedoSubLoopNum.Text;
			handleChanges();
		}

        private void albedoSubLoopNumLock_Unchecked(object sender, RoutedEventArgs e)
        {
			if (!albedoSubLoopNumY.IsEnabled)
			{
				albedoSubLoopNumY.IsEnabled = true;
			}
			this.albedoSubLoopNumY.Value = this.albedoSubLoopNumX.Value;
			this.material.AlbedoSubLoopNum = this.albedoSubLoopNum.Text;
			handleChanges();
		}

        private void albedoSubLoopNumX_DragCompleted(object sender, System.Windows.Controls.Primitives.DragCompletedEventArgs e)
        {
			if (albedoSubLoopNumLock.IsChecked == true)
			{
				this.albedoSubLoopNumY.Value = this.albedoSubLoopNumX.Value;
			}
			this.albedoSubLoopNum.Text = assignLoopNum(this.albedoSubLoopNumX.Value, this.albedoSubLoopNumY.Value);
			this.material.AlbedoSubLoopNum = this.albedoSubLoopNum.Text;
			handleChanges();
		}

        private void albedoSubLoopNumY_DragCompleted(object sender, System.Windows.Controls.Primitives.DragCompletedEventArgs e)
        {
			if (albedoSubLoopNumLock.IsChecked == false)
			{
				this.albedoSubLoopNum.Text = assignLoopNum(this.albedoSubLoopNumX.Value, this.albedoSubLoopNumY.Value);
				this.material.AlbedoSubLoopNum = this.albedoSubLoopNum.Text;
			}
			handleChanges();
		}

		private void albedoSubLoopNumX_ValueChanged(object sender, RoutedPropertyChangedEventArgs<double> e)
		{
			if (albedoSubLoopNumLock.IsChecked == true)
			{
				this.albedoSubLoopNumY.Value = this.albedoSubLoopNumX.Value;
			}
			this.albedoSubLoopNum.Text = assignLoopNum(this.albedoSubLoopNumX.Value, this.albedoSubLoopNumY.Value);
		}

		private void albedoSubLoopNumY_ValueChanged(object sender, RoutedPropertyChangedEventArgs<double> e)
		{
			if (albedoSubLoopNumLock.IsChecked == false)
			{
				this.albedoSubLoopNum.Text = assignLoopNum(this.albedoSubLoopNumX.Value, this.albedoSubLoopNumY.Value);
			}
		}

		private void albedoSubLoopNumReset_Click(object sender, RoutedEventArgs e)
		{
			this.material.AlbedoSubLoopNum = resetLoopNum(this.albedoSubLoopNum, this.albedoSubLoopNumX, this.albedoSubLoopNumY);
			handleChanges();
		}

		/* END ALBEDO SUB */
		/* ALPHA */
        private void alphaMapFrom_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
			this.material.AlphaMapFrom = alphaMapFrom.SelectedIndex;
			handleChanges();
		}

        private void alphaMapUVFlip_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
			this.material.AlphaMapUVFlip = alphaMapUVFlip.SelectedIndex;
			handleChanges();
		}

        private void alphaMapSwizzle_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
			this.material.AlphaMapSwizzle = alphaMapSwizzle.SelectedIndex;
			handleChanges();
		}

        private void alphaMapFileButton_Click(object sender, RoutedEventArgs e)
        {
			String mapFile = handleOpenDialog(imageFilters);
			if (!mapFile.Equals(""))
			{
				this.material.AlphaMapFile = Util.GetRelativePath(this.material.FilePath, mapFile);
				this.alphaMapFile.Content = this.material.AlphaMapFile;
				handleChanges();
			}
		}

		private void alphaLinearColor_DragCompleted(object sender, System.Windows.Controls.Primitives.DragCompletedEventArgs e)
		{
			this.material.Alpha = float.Parse(this.alpha.Text);
			handleChanges();
		}

		private void alpha_PreviewTextInput(object sender, TextCompositionEventArgs e)
        {
			handleTextInput(ref sender, ref e);
		}

        private void alphaLoopNumLock_Checked(object sender, RoutedEventArgs e)
        {
			if (alphaLoopNumY.IsEnabled)
			{
				alphaLoopNumY.IsEnabled = false;
			}
			this.alphaLoopNumY.Value = this.alphaLoopNumX.Value;
			this.alphaLoopNum.Text = assignLoopNum(this.alphaLoopNumX.Value, this.alphaLoopNumY.Value);
			this.material.AlphaLoopNum = this.alphaLoopNum.Text;
			handleChanges();
		}

        private void alphaLoopNumLock_Unchecked(object sender, RoutedEventArgs e)
        {
			if (!alphaLoopNumY.IsEnabled)
			{
				alphaLoopNumY.IsEnabled = true;
			}
			this.alphaLoopNumY.Value = this.alphaLoopNumX.Value;
			this.material.AlphaLoopNum = this.alphaLoopNum.Text;
			handleChanges();
		}

        private void alphaLoopNumX_DragCompleted(object sender, System.Windows.Controls.Primitives.DragCompletedEventArgs e)
        {
			if (alphaLoopNumLock.IsChecked == true)
			{
				this.alphaLoopNumY.Value = this.alphaLoopNumX.Value;
			}
			this.alphaLoopNum.Text = assignLoopNum(this.alphaLoopNumX.Value, this.alphaLoopNumY.Value);
			this.material.AlphaLoopNum = this.alphaLoopNum.Text;
			handleChanges();
		}

        private void alphaLoopNumY_DragCompleted(object sender, System.Windows.Controls.Primitives.DragCompletedEventArgs e)
        {
			if (alphaLoopNumLock.IsChecked == false)
			{
				this.alphaLoopNum.Text = assignLoopNum(this.alphaLoopNumX.Value, this.alphaLoopNumY.Value);
				this.material.AlphaLoopNum = this.alphaLoopNum.Text;
			}
			handleChanges();
		}

        private void alphaLoopNumX_ValueChanged(object sender, RoutedPropertyChangedEventArgs<double> e)
        {
			if (alphaLoopNumLock.IsChecked == true)
			{
				this.alphaLoopNumY.Value = this.alphaLoopNumX.Value;
			}
			this.alphaLoopNum.Text = assignLoopNum(this.alphaLoopNumX.Value, this.alphaLoopNumY.Value);
		}

        private void alphaLoopNumY_ValueChanged(object sender, RoutedPropertyChangedEventArgs<double> e)
        {
			if (alphaLoopNumLock.IsChecked == false)
			{
				this.alphaLoopNum.Text = assignLoopNum(this.alphaLoopNumX.Value, this.alphaLoopNumY.Value);
			}
		}

        private void alphaLoopNumReset_Click(object sender, RoutedEventArgs e)
        {
			this.material.AlphaLoopNum = resetLoopNum(this.alphaLoopNum, this.alphaLoopNumX, this.alphaLoopNumY);
			handleChanges();
		}

        private void alpha_KeyUp(object sender, KeyEventArgs e)
        {
			if (this.material.Alpha != float.Parse(this.alpha.Text))
            {
				this.material.Alpha = float.Parse(this.alpha.Text);
				handleChanges();
			}
		}
		/*END ALPHA*/
		/*NORMAL*/

		private void normalMapFrom_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.NormalMapFrom = normalMapFrom.SelectedIndex;
			handleChanges();
		}

		private void normalMapUVFlip_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.NormalMapUVFlip = normalMapUVFlip.SelectedIndex;
			handleChanges();
		}

		private void normalMapType_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.NormalMapType = normalMapType.SelectedIndex;
			handleChanges();
		}


		private void normalMapFile_Click(object sender, RoutedEventArgs e)
		{
			String mapFile = handleOpenDialog(imageFilters);
			if (!mapFile.Equals(""))
			{
				this.material.NormalMapFile = Util.GetRelativePath(this.material.FilePath, mapFile);
				this.normalMapFile.Content = this.material.NormalMapFile;
				handleChanges();
			}
		}

		private void normal_PreviewTextInput(object sender, TextCompositionEventArgs e)
		{
			handleTextInput(ref sender, ref e);
		}


		private void normal_KeyUp(object sender, KeyEventArgs e)
		{
			if (this.material.Normal != float.Parse(this.normal.Text))
			{
				this.material.Normal = float.Parse(this.normal.Text);
				handleChanges();
			}
		}

		private void normalLoopNumLock_Checked(object sender, RoutedEventArgs e)
		{
			if (normalLoopNumY.IsEnabled)
			{
				normalLoopNumY.IsEnabled = false;
			}
			this.normalLoopNumY.Value = this.normalLoopNumX.Value;
			this.normalLoopNum.Text = assignLoopNum(this.normalLoopNumX.Value, this.normalLoopNumY.Value);
			this.material.NormalLoopNum = this.normalLoopNum.Text;
			handleChanges();
		}

		private void normalLoopNumLock_Unchecked(object sender, RoutedEventArgs e)
		{
			if (!normalLoopNumY.IsEnabled)
			{
				normalLoopNumY.IsEnabled = true;
			}
			this.normalLoopNumY.Value = this.normalLoopNumX.Value;
			this.material.NormalLoopNum = this.normalLoopNum.Text;
			handleChanges();
		}

		private void normalLoopNumX_DragCompleted(object sender, System.Windows.Controls.Primitives.DragCompletedEventArgs e)
		{
			if (normalLoopNumLock.IsChecked == true)
			{
				this.normalLoopNumY.Value = this.normalLoopNumX.Value;
			}
			this.normalLoopNum.Text = assignLoopNum(this.normalLoopNumX.Value, this.normalLoopNumY.Value);
			this.material.NormalLoopNum = this.normalLoopNum.Text;
			handleChanges();
		}

		private void normalLoopNumY_DragCompleted(object sender, System.Windows.Controls.Primitives.DragCompletedEventArgs e)
		{
			if (normalLoopNumLock.IsChecked == false)
			{
				this.normalLoopNum.Text = assignLoopNum(this.normalLoopNumX.Value, this.normalLoopNumY.Value);
				this.material.NormalLoopNum = this.normalLoopNum.Text;
			}
			handleChanges();
		}

		private void normalLinearColor_DragCompleted(object sender, System.Windows.Controls.Primitives.DragCompletedEventArgs e)
		{
			this.material.Normal = float.Parse(normal.Text);
			handleChanges();
		}

		private void normalLoopNumX_ValueChanged(object sender, RoutedPropertyChangedEventArgs<double> e)
		{
			if (normalLoopNumLock.IsChecked == true)
			{
				this.normalLoopNumY.Value = this.normalLoopNumX.Value;
			}
			this.normalLoopNum.Text = assignLoopNum(this.normalLoopNumX.Value, this.normalLoopNumY.Value);
		}

		private void normalLoopNumY_ValueChanged(object sender, RoutedPropertyChangedEventArgs<double> e)
		{
			if (normalLoopNumLock.IsChecked == false)
			{
				this.normalLoopNum.Text = assignLoopNum(this.normalLoopNumX.Value, this.normalLoopNumY.Value);
			}
		}

		private void normalLoopNumReset_Click(object sender, RoutedEventArgs e)
		{
			this.material.NormalLoopNum = resetLoopNum(this.normalLoopNum, this.normalLoopNumX, this.normalLoopNumY);
			handleChanges();
		}
		/*END NORMAL*/
		/*NORMAL SUB*/
		private void normalSubMapFrom_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.NormalSubMapFrom = normalSubMapFrom.SelectedIndex;
			handleChanges();
		}

		private void normalSubMapUVFlip_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.NormalSubMapUVFlip = normalSubMapUVFlip.SelectedIndex;
			handleChanges();
		}

		private void normalSubMapType_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.NormalSubMapType = normalSubMapType.SelectedIndex;
			handleChanges();
		}


		private void normalSubMapFile_Click(object sender, RoutedEventArgs e)
		{
			String mapFile = handleOpenDialog(imageFilters);
			if (!mapFile.Equals(""))
			{
				this.material.NormalSubMapFile = Util.GetRelativePath(this.material.FilePath, mapFile);
				this.normalSubMapFile.Content = this.material.NormalSubMapFile;
				handleChanges();
			}
		}

		private void normalSub_PreviewTextInput(object sender, TextCompositionEventArgs e)
		{
			handleTextInput(ref sender, ref e);
		}


		private void normalSub_KeyUp(object sender, KeyEventArgs e)
		{
			if (this.material.NormalSub != float.Parse(this.normalSub.Text))
			{
				this.material.NormalSub = float.Parse(this.normalSub.Text);
				handleChanges();
			}
		}

		private void normalSubLoopNumLock_Checked(object sender, RoutedEventArgs e)
		{
			if (normalSubLoopNumY.IsEnabled)
			{
				normalSubLoopNumY.IsEnabled = false;
			}
			this.normalSubLoopNumY.Value = this.normalSubLoopNumX.Value;
			this.normalSubLoopNum.Text = assignLoopNum(this.normalSubLoopNumX.Value, this.normalSubLoopNumY.Value);
			this.material.NormalSubLoopNum = this.normalSubLoopNum.Text;
			handleChanges();
		}

		private void normalSubLoopNumLock_Unchecked(object sender, RoutedEventArgs e)
		{
			if (!normalSubLoopNumY.IsEnabled)
			{
				normalSubLoopNumY.IsEnabled = true;
			}
			this.normalSubLoopNumY.Value = this.normalSubLoopNumX.Value;
			this.material.NormalSubLoopNum = this.normalSubLoopNum.Text;
			handleChanges();
		}

		private void normalSubLoopNumX_DragCompleted(object sender, System.Windows.Controls.Primitives.DragCompletedEventArgs e)
		{
			if (normalSubLoopNumLock.IsChecked == true)
			{
				this.normalSubLoopNumY.Value = this.normalSubLoopNumX.Value;
			}
			this.normalSubLoopNum.Text = assignLoopNum(this.normalSubLoopNumX.Value, this.normalSubLoopNumY.Value);
			this.material.NormalSubLoopNum = this.normalSubLoopNum.Text;
			handleChanges();
		}

		private void normalSubLoopNumY_DragCompleted(object sender, System.Windows.Controls.Primitives.DragCompletedEventArgs e)
		{
			if (normalSubLoopNumLock.IsChecked == false)
			{
				this.normalSubLoopNum.Text = assignLoopNum(this.normalSubLoopNumX.Value, this.normalSubLoopNumY.Value);
				this.material.NormalSubLoopNum = this.normalSubLoopNum.Text;
			}
			handleChanges();
		}

		private void normalSubLinearColor_DragCompleted(object sender, System.Windows.Controls.Primitives.DragCompletedEventArgs e)
		{
			this.material.NormalSub = float.Parse(normalSub.Text);
			handleChanges();
		}

		private void normalSubLoopNumX_ValueChanged(object sender, RoutedPropertyChangedEventArgs<double> e)
		{
			if (normalSubLoopNumLock.IsChecked == true)
			{
				this.normalSubLoopNumY.Value = this.normalSubLoopNumX.Value;
			}
			this.normalSubLoopNum.Text = assignLoopNum(this.normalSubLoopNumX.Value, this.normalSubLoopNumY.Value);
		}

		private void normalSubLoopNumY_ValueChanged(object sender, RoutedPropertyChangedEventArgs<double> e)
		{
			if (normalSubLoopNumLock.IsChecked == false)
			{
				this.normalSubLoopNum.Text = assignLoopNum(this.normalSubLoopNumX.Value, this.normalSubLoopNumY.Value);
			}
		}

		private void normalSubLoopNumReset_Click(object sender, RoutedEventArgs e)
		{
			this.material.NormalSubLoopNum = resetLoopNum(this.normalSubLoopNum, this.normalSubLoopNumX, this.normalSubLoopNumY);
			handleChanges();
		}
		/*END SUB NORMAL*/
		/* SMOOTHNESS */
		private void smoothnessMapFrom_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.SmoothnessMapFrom = smoothnessMapFrom.SelectedIndex;
			handleChanges();
		}

		private void smoothnessMapUVFlip_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.SmoothnessMapUVFlip = smoothnessMapUVFlip.SelectedIndex;
			handleChanges();
		}

		private void smoothnessMapType_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.SmoothnessMapType = smoothnessMapType.SelectedIndex;
			handleChanges();
		}

		private void smoothnessMapSwizzle_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.SmoothnessMapSwizzle = smoothnessMapSwizzle.SelectedIndex;
			handleChanges();
		}

		private void smoothnessMapApplyScale_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.SmoothnessMapApplyScale = smoothnessMapApplyScale.SelectedIndex;
			handleChanges();
		}

		private void smoothnessMapFile_Click(object sender, RoutedEventArgs e)
		{
			String mapFile = handleOpenDialog(imageFilters);
			if (!mapFile.Equals(""))
			{
				this.material.SmoothnessMapFile = Util.GetRelativePath(this.material.FilePath, mapFile);
				this.smoothnessMapFile.Content = this.material.SmoothnessMapFile;
				handleChanges();
			}
		}

		private void smoothness_PreviewTextInput(object sender, TextCompositionEventArgs e)
		{
			handleTextInput(ref sender, ref e);
		}


		private void smoothness_KeyUp(object sender, KeyEventArgs e)
		{
			if (this.material.Smoothness != float.Parse(this.smoothness.Text))
			{
				this.material.Smoothness = float.Parse(this.smoothness.Text);
				handleChanges();
			}
		}

		private void smoothnessLoopNumLock_Checked(object sender, RoutedEventArgs e)
		{
			if (smoothnessLoopNumY.IsEnabled)
			{
				smoothnessLoopNumY.IsEnabled = false;
			}
			this.smoothnessLoopNumY.Value = this.smoothnessLoopNumX.Value;
			this.smoothnessLoopNum.Text = assignLoopNum(this.smoothnessLoopNumX.Value, this.smoothnessLoopNumY.Value);
			this.material.SmoothnessLoopNum = this.smoothnessLoopNum.Text;
			handleChanges();
		}

		private void smoothnessLoopNumLock_Unchecked(object sender, RoutedEventArgs e)
		{
			if (!smoothnessLoopNumY.IsEnabled)
			{
				smoothnessLoopNumY.IsEnabled = true;
			}
			this.smoothnessLoopNumY.Value = this.smoothnessLoopNumX.Value;
			this.material.SmoothnessLoopNum = this.smoothnessLoopNum.Text;
			handleChanges();
		}

		private void smoothnessLoopNumX_DragCompleted(object sender, System.Windows.Controls.Primitives.DragCompletedEventArgs e)
		{
			if (smoothnessLoopNumLock.IsChecked == true)
			{
				this.smoothnessLoopNumY.Value = this.smoothnessLoopNumX.Value;
			}
			this.smoothnessLoopNum.Text = assignLoopNum(this.smoothnessLoopNumX.Value, this.smoothnessLoopNumY.Value);
			this.material.SmoothnessLoopNum = this.smoothnessLoopNum.Text;
			handleChanges();
		}

		private void smoothnessLoopNumY_DragCompleted(object sender, System.Windows.Controls.Primitives.DragCompletedEventArgs e)
		{
			if (smoothnessLoopNumLock.IsChecked == false)
			{
				this.smoothnessLoopNum.Text = assignLoopNum(this.smoothnessLoopNumX.Value, this.smoothnessLoopNumY.Value);
				this.material.SmoothnessLoopNum = this.smoothnessLoopNum.Text;
			}
			handleChanges();
		}

		private void smoothnessLinearColor_DragCompleted(object sender, System.Windows.Controls.Primitives.DragCompletedEventArgs e)
		{
			this.material.Smoothness = float.Parse(smoothness.Text);
			handleChanges();
		}

		private void smoothnessLoopNumX_ValueChanged(object sender, RoutedPropertyChangedEventArgs<double> e)
		{
			if (smoothnessLoopNumLock.IsChecked == true)
			{
				this.smoothnessLoopNumY.Value = this.smoothnessLoopNumX.Value;
			}
			this.smoothnessLoopNum.Text = assignLoopNum(this.smoothnessLoopNumX.Value, this.smoothnessLoopNumY.Value);
		}

		private void smoothnessLoopNumY_ValueChanged(object sender, RoutedPropertyChangedEventArgs<double> e)
		{
			if (smoothnessLoopNumLock.IsChecked == false)
			{
				this.smoothnessLoopNum.Text = assignLoopNum(this.smoothnessLoopNumX.Value, this.smoothnessLoopNumY.Value);
			}
		}

		private void smoothnessLoopNumReset_Click(object sender, RoutedEventArgs e)
		{
			this.material.SmoothnessLoopNum = resetLoopNum(this.smoothnessLoopNum, this.smoothnessLoopNumX, this.smoothnessLoopNumY);
			handleChanges();
		}
		/*END SMOOTHNESS*/
		/*METALNESS*/
		private void metalnessMapFrom_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.MetalnessMapFrom = metalnessMapFrom.SelectedIndex;
			handleChanges();
		}

		private void metalnessMapUVFlip_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.MetalnessMapUVFlip = metalnessMapUVFlip.SelectedIndex;
			handleChanges();
		}

		private void metalnessMapSwizzle_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.MetalnessMapSwizzle = metalnessMapSwizzle.SelectedIndex;
			handleChanges();
		}

		private void metalnessMapApplyScale_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.MetalnessMapApplyScale = metalnessMapApplyScale.SelectedIndex;
			handleChanges();
		}

		private void metalnessMapFile_Click(object sender, RoutedEventArgs e)
		{
			String mapFile = handleOpenDialog(imageFilters);
			if (!mapFile.Equals(""))
			{
				this.material.MetalnessMapFile = Util.GetRelativePath(this.material.FilePath, mapFile);
				this.metalnessMapFile.Content = this.material.MetalnessMapFile;
				handleChanges();
			}
		}

		private void metalness_PreviewTextInput(object sender, TextCompositionEventArgs e)
		{
			handleTextInput(ref sender, ref e);
		}


		private void metalness_KeyUp(object sender, KeyEventArgs e)
		{
			if (this.material.Metalness != float.Parse(this.metalness.Text))
			{
				this.material.Metalness = float.Parse(this.metalness.Text);
				handleChanges();
			}
		}

		private void metalnessLoopNumLock_Checked(object sender, RoutedEventArgs e)
		{
			if (metalnessLoopNumY.IsEnabled)
			{
				metalnessLoopNumY.IsEnabled = false;
			}
			this.metalnessLoopNumY.Value = this.metalnessLoopNumX.Value;
			this.metalnessLoopNum.Text = assignLoopNum(this.metalnessLoopNumX.Value, this.metalnessLoopNumY.Value);
			this.material.MetalnessLoopNum = this.metalnessLoopNum.Text;
			handleChanges();
		}

		private void metalnessLoopNumLock_Unchecked(object sender, RoutedEventArgs e)
		{
			if (!metalnessLoopNumY.IsEnabled)
			{
				metalnessLoopNumY.IsEnabled = true;
			}
			this.metalnessLoopNumY.Value = this.metalnessLoopNumX.Value;
			this.material.MetalnessLoopNum = this.metalnessLoopNum.Text;
			handleChanges();
		}

		private void metalnessLoopNumX_DragCompleted(object sender, System.Windows.Controls.Primitives.DragCompletedEventArgs e)
		{
			if (metalnessLoopNumLock.IsChecked == true)
			{
				this.metalnessLoopNumY.Value = this.metalnessLoopNumX.Value;
			}
			this.metalnessLoopNum.Text = assignLoopNum(this.metalnessLoopNumX.Value, this.metalnessLoopNumY.Value);
			this.material.MetalnessLoopNum = this.metalnessLoopNum.Text;
			handleChanges();
		}

		private void metalnessLoopNumY_DragCompleted(object sender, System.Windows.Controls.Primitives.DragCompletedEventArgs e)
		{
			if (metalnessLoopNumLock.IsChecked == false)
			{
				this.metalnessLoopNum.Text = assignLoopNum(this.metalnessLoopNumX.Value, this.metalnessLoopNumY.Value);
				this.material.MetalnessLoopNum = this.metalnessLoopNum.Text;
			}
			handleChanges();
		}

		private void metalnessLinearColor_DragCompleted(object sender, System.Windows.Controls.Primitives.DragCompletedEventArgs e)
		{
			this.material.Metalness = float.Parse(metalness.Text);
			handleChanges();
		}

		private void metalnessLoopNumX_ValueChanged(object sender, RoutedPropertyChangedEventArgs<double> e)
		{
			if (metalnessLoopNumLock.IsChecked == true)
			{
				this.metalnessLoopNumY.Value = this.metalnessLoopNumX.Value;
			}
			this.metalnessLoopNum.Text = assignLoopNum(this.metalnessLoopNumX.Value, this.metalnessLoopNumY.Value);
		}

		private void metalnessLoopNumY_ValueChanged(object sender, RoutedPropertyChangedEventArgs<double> e)
		{
			if (metalnessLoopNumLock.IsChecked == false)
			{
				this.metalnessLoopNum.Text = assignLoopNum(this.metalnessLoopNumX.Value, this.metalnessLoopNumY.Value);
			}
		}

		private void metalnessLoopNumReset_Click(object sender, RoutedEventArgs e)
		{
			this.material.MetalnessLoopNum = resetLoopNum(this.metalnessLoopNum, this.metalnessLoopNumX, this.metalnessLoopNumY);
			handleChanges();
		}
		/*END METALNESS*/
		/*OCCLUSION*/
		private void occlusionMapFrom_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.OcclusionMapFrom = occlusionMapFrom.SelectedIndex;
			handleChanges();
		}

		private void occlusionMapUVFlip_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.OcclusionMapUVFlip = occlusionMapUVFlip.SelectedIndex;
			handleChanges();
		}

		private void occlusionMapType_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.OcclusionMapType = occlusionMapType.SelectedIndex;
			handleChanges();
		}

		private void occlusionMapSwizzle_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.OcclusionMapSwizzle = occlusionMapSwizzle.SelectedIndex;
			handleChanges();
		}

		private void occlusionMapApplyScale_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			this.material.OcclusionMapApplyScale = occlusionMapApplyScale.SelectedIndex;
			handleChanges();
		}

		private void occlusionMapFile_Click(object sender, RoutedEventArgs e)
		{
			String mapFile = handleOpenDialog(imageFilters);
			if (!mapFile.Equals(""))
			{
				this.material.OcclusionMapFile = Util.GetRelativePath(this.material.FilePath, mapFile);
				this.occlusionMapFile.Content = this.material.OcclusionMapFile;
				handleChanges();
			}
		}

		private void occlusion_PreviewTextInput(object sender, TextCompositionEventArgs e)
		{
			handleTextInput(ref sender, ref e);
		}


		private void occlusion_KeyUp(object sender, KeyEventArgs e)
		{
			if (this.material.Occlusion != float.Parse(this.occlusion.Text))
			{
				this.material.Occlusion = float.Parse(this.occlusion.Text);
				handleChanges();
			}
		}

		private void occlusionLoopNumLock_Checked(object sender, RoutedEventArgs e)
		{
			if (occlusionLoopNumY.IsEnabled)
			{
				occlusionLoopNumY.IsEnabled = false;
			}
			this.occlusionLoopNumY.Value = this.occlusionLoopNumX.Value;
			this.occlusionLoopNum.Text = assignLoopNum(this.occlusionLoopNumX.Value, this.occlusionLoopNumY.Value);
			this.material.OcclusionLoopNum = this.occlusionLoopNum.Text;
			handleChanges();
		}

		private void occlusionLoopNumLock_Unchecked(object sender, RoutedEventArgs e)
		{
			if (!occlusionLoopNumY.IsEnabled)
			{
				occlusionLoopNumY.IsEnabled = true;
			}
			this.occlusionLoopNumY.Value = this.occlusionLoopNumX.Value;
			this.material.OcclusionLoopNum = this.occlusionLoopNum.Text;
			handleChanges();
		}

		private void occlusionLoopNumX_DragCompleted(object sender, System.Windows.Controls.Primitives.DragCompletedEventArgs e)
		{
			if (occlusionLoopNumLock.IsChecked == true)
			{
				this.occlusionLoopNumY.Value = this.occlusionLoopNumX.Value;
			}
			this.occlusionLoopNum.Text = assignLoopNum(this.occlusionLoopNumX.Value, this.occlusionLoopNumY.Value);
			this.material.OcclusionLoopNum = this.occlusionLoopNum.Text;
			handleChanges();
		}

		private void occlusionLoopNumY_DragCompleted(object sender, System.Windows.Controls.Primitives.DragCompletedEventArgs e)
		{
			if (occlusionLoopNumLock.IsChecked == false)
			{
				this.occlusionLoopNum.Text = assignLoopNum(this.occlusionLoopNumX.Value, this.occlusionLoopNumY.Value);
				this.material.OcclusionLoopNum = this.occlusionLoopNum.Text;
			}
			handleChanges();
		}

		private void occlusionLinearColor_DragCompleted(object sender, System.Windows.Controls.Primitives.DragCompletedEventArgs e)
		{
			this.material.Occlusion = float.Parse(occlusion.Text);
			handleChanges();
		}

		private void occlusionLoopNumX_ValueChanged(object sender, RoutedPropertyChangedEventArgs<double> e)
		{
			if (occlusionLoopNumLock.IsChecked == true)
			{
				this.occlusionLoopNumY.Value = this.occlusionLoopNumX.Value;
			}
			this.occlusionLoopNum.Text = assignLoopNum(this.occlusionLoopNumX.Value, this.occlusionLoopNumY.Value);
		}

		private void occlusionLoopNumY_ValueChanged(object sender, RoutedPropertyChangedEventArgs<double> e)
		{
			if (occlusionLoopNumLock.IsChecked == false)
			{
				this.occlusionLoopNum.Text = assignLoopNum(this.occlusionLoopNumX.Value, this.occlusionLoopNumY.Value);
			}
		}

		private void occlusionLoopNumReset_Click(object sender, RoutedEventArgs e)
		{
			this.material.OcclusionLoopNum = resetLoopNum(this.occlusionLoopNum, this.occlusionLoopNumX, this.occlusionLoopNumY);
			handleChanges();
		}
	}
}
