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
using RMT.Model;
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

		private EMM project;
	
		private RayMaterial material;

		public bool initializationEnded = false;
		//Directory where the materials are created by default... TODO
		private String applicationBaseCreationPath = Directory.GetCurrentDirectory();
		private string imageFilters = "All Graphics Types|*.bmp;*.jpg;*.jpeg;*.png;*.tif;*.tiff"+ "BMP(*.bmp)|*.bmp|GIF(*.gif)|*.gif|JPG(*.jpg)|*.jpg;*.jpeg|PNG(*.png)|*.png|TIFF(*.tif)|*.tif;*.tiff|";

		//Handling ColorPicker changes with variables, since the event triggers the ColorChanged even when you're dragging your cursor
		private String albedoSelectedColor = null;

		public MainWindow()
		{
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
						MessageBox.Show("File type not recognised.\nMake sure the file extension\nis correct."	);
						break;
				}
			}
		}

		private void InitializeValues()
		{
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

		private String handleOpenDialog(String initialDirectory = "c:\\", String filter = "Material files (*.fx)|*.fx", String title = "Select map file")
		{
			OpenFileDialog openFileDialog = new OpenFileDialog();

			openFileDialog.InitialDirectory = initialDirectory;
			openFileDialog.Filter = filter;
			openFileDialog.Title = title;
			openFileDialog.AddExtension = true;
			openFileDialog.RestoreDirectory = true;

			bool? result = openFileDialog.ShowDialog();
			if (result == true)
			{
				//Get the path of specified file
				return openFileDialog.FileName;
			}
			return "";
		}

		private String GetFileName(String filePath)
		{
			return filePath.Substring(filePath.LastIndexOf("\\")+1);
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

		//UI Events

		private void newMaterial_Click(object sender, RoutedEventArgs e)
		{
			createNewMaterial();
		}
	
		private void editMaterial_Click(object sender, RoutedEventArgs e)
		{
			openMaterial(this.handleOpenDialog(Directory.GetCurrentDirectory(), "Material files (*.fx)|*.fx", "Open material file"));
		}
	
		private void loadProject_Click(object sender, RoutedEventArgs e)
		{
			openProject(this.handleOpenDialog(Directory.GetCurrentDirectory(), "MME project files (*.emm)|*.emm", "Open project file"));
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
			String mapFile = handleOpenDialog("c:\\", imageFilters);
			if (!mapFile.Equals(""))
			{
				this.material.AlbedoMapFile = Util.GetRelativePath(this.material.FilePath, mapFile);
				this.albedoMapFile.Content = this.material.AlbedoMapFile;
				handleChanges();
			}
		}

		private void albedoMode_SelectionChanged(object sender, SelectionChangedEventArgs e)
		{
			switch (this.albedoMode.SelectedIndex)
			{
				case (int) MAP_MODES.LINEAR_COLOR:
					albedoLinearColor.Visibility = Visibility.Visible;
					albedoRGB.Visibility = Visibility.Hidden;
					albedo.Visibility = Visibility.Visible;
					if (this.albedo.Text.Contains("float"))
						this.albedo.Text = "1.0";
					this.material.Albedo = this.albedo.Text;
					break;
				case (int) MAP_MODES.SRGB:
					albedoLinearColor.Visibility = Visibility.Hidden;
					albedoRGB.Visibility = Visibility.Visible;
					albedo.Visibility = Visibility.Hidden;

					if (this.albedoSelectedColor != null)
					{
						this.material.Albedo = this.UpdateRGBColor(this.albedoMode.SelectedIndex, this.albedoSelectedColor);
					} else
                    {
						byte [] rgbValues = this.fetchRGBFromString(this.material.Albedo);
						Color color = Color.FromRgb(rgbValues[0],rgbValues[1],rgbValues[2]);
						albedoRGB.SelectedColor = color;
					}
					break;
				case (int)MAP_MODES.LINEAR_SRGB:
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
					break;
			}
			handleChanges();
		}
		/*
		 * Function that converts a String of type float2(x,y,z) into an array of rgb like: byte[0] = x, byte[1] = y, byte[2] = z
		 */
		private byte[] fetchRGBFromString(string str)
        {
			str = str.Remove(0, str.LastIndexOf("(")+1);
			int end = str.IndexOf(")");
			string commaRGBValues = str.Substring(0, end );
			String [] rgbValues = commaRGBValues.Split(',');
			return  new byte []{ Convert.ToByte(Int32.Parse(rgbValues[0])), Convert.ToByte(Int32.Parse(rgbValues[1])), Convert.ToByte(Int32.Parse(rgbValues[2])) } ;
        }

        private void albedo_PreviewTextInput(object sender, TextCompositionEventArgs e)
		{
			bool approvedDecimalPoint = false;
			if (e.Text == ".")
			{
				if (!((TextBox)sender).Text.Contains("."))
					approvedDecimalPoint = true;
			}

			if (!(char.IsDigit(e.Text, e.Text.Length - 1) || approvedDecimalPoint))
				e.Handled = true;

			this.material.Albedo = this.albedo.Text;
			handleChanges();
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
			this.albedoLoopNum.Text = assignLoopNum(1.00f, 1.00f);
			this.material.AlbedoLoopNum = this.albedoLoopNum.Text;
			this.albedoLoopNumX.Value = 1.0f;
			this.albedoLoopNumY.Value = 1.0f;
			handleChanges();
		}
		/*END ALBEDO*/
	}
}
