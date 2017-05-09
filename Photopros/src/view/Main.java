package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import controller.ImageProcess;

import javax.imageio.ImageIO;

/*import controller.MyTool;
import controller.ProcessImages;
import controller.StorePrintStream;*/

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;
import java.awt.Font;
import java.awt.Panel;

public class Main {

	private static ArrayList<File> fImagesCollection = new ArrayList<File>();
	private static ArrayList<File> RImagesCollection = new ArrayList<File>();
	private GridLayout grid_PictureSelect = new GridLayout(Integer.valueOf(fImagesCollection.size())*3, 3,50,50);
	
	private JFrame imageProcess;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton btnReset;
	private JButton btnProcess;
	private JButton btnHome;
	private JButton btnResult;
	private static String sDirectoryA;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					String sDrive = null;
					
					String sDriveC = "C:\\";
					String sDriveD = "D:\\";
					File fDriveC = new File(sDriveC);
					File fDriveD = new File(sDriveD);
					
					if (fDriveC.exists()) {
						sDrive = sDriveC;
					}else if (fDriveD.exists()) {
						sDrive = sDriveD;
					}else 
					{
						sDrive = "<unknown>:\\";
						JOptionPane.showMessageDialog(null, "Drive "+sDriveC+" or "+sDriveD+" does not exist in this machine.", "Warning",JOptionPane.ERROR_MESSAGE);
					}
					
					sDirectoryA = sDrive+"ImagePro\\oriImage_Upload";
					String sFailMsgA = "";
					boolean bfailA = false;
					File fDirectoryA = new File(sDirectoryA);
					if (!fDirectoryA.exists()) {
			            if (fDirectoryA.mkdirs()) {
			                System.out.println("Path directory of \""+sDirectoryA+"\" was created!");
			            } else {
			            	bfailA = true;
			            	sFailMsgA = "Failed to create path directory of \""+sDirectoryA+"\" ! Check if this machine has drive either "+sDriveC+" or "+sDriveD+"  \n";
			                System.out.print(sFailMsgA);
			            }
			        }else {
			        	System.out.println("Path directory of \""+sDirectoryA+"\" was already exist!");
			        }
					
					String sDirectoryB = sDrive+"ImagePro\\processed";
					String sFailMsgB = "";
					boolean bfailB = false;
					File fDirectoryB = new File(sDirectoryB);
					if (!fDirectoryB.exists()) {
			            if (fDirectoryB.mkdirs()) {
			                System.out.println("Path directory of \""+sDirectoryB+"\" was created!");
			            } else {
			            	bfailB = true;
			            	sFailMsgB = "Failed to create path directory of \""+sDirectoryB+"\" ! Check if this machine has drive either "+sDriveC+" or "+sDriveD+"  \n";
			                System.out.print(sFailMsgB);
			            }
			        }else {
			        	System.out.println("Path directory of \""+sDirectoryB+"\" was already exist!");
			        }
					
					String sDirectoryC = sDrive+"ImagePro\\Result";
					String sFailMsgC = "";
					boolean bfailC = false;
					File fDirectoryC = new File(sDirectoryC);
					if (!fDirectoryC.exists()) {
			            if (fDirectoryC.mkdirs()) {
			                System.out.println("Path directory of \""+sDirectoryC+"\" was created!");
			            } else {
			            	bfailC = true;
			            	sFailMsgC = "Failed to create path directory of \""+sDirectoryC+"\" ! Check if this machine has drive either "+sDriveC+" or "+sDriveD+"  \n";
			                System.out.print(sFailMsgC);
			            }
			        }else {
			        	System.out.println("Path directory of \""+sDirectoryC+"\" was already exist!");
			        }
					

					if(bfailA==true || bfailB==true || bfailC==true)
			        {
			        	JOptionPane.showMessageDialog(null, sFailMsgA+sFailMsgB+sFailMsgC, "Warning",JOptionPane.ERROR_MESSAGE);
			        }
				
					
					Main window = new Main(sDrive);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main(String sDrive) {
		initialize(sDrive);
		imageProcess.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final String sDrive) {
		try {UIManager.setLookAndFeel(new NimbusLookAndFeel());} catch (UnsupportedLookAndFeelException e) {e.printStackTrace();}
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int widthScreen = gd.getDisplayMode().getWidth()-30;
		int heightScreen = gd.getDisplayMode().getHeight()-100;
		
		imageProcess = new JFrame();
		imageProcess.setTitle("Image Processing");
		imageProcess.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		imageProcess.setResizable(true);
		imageProcess.setBounds(0, 0, widthScreen+6, heightScreen+33);
		imageProcess.setVisible(true);
		imageProcess.setLocationRelativeTo(null);
		imageProcess.getContentPane().setLayout(null);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 JFileChooser fc = new JFileChooser();
				    fc.setFileFilter(new JPEGImageFileFilter());
				    fc.setMultiSelectionEnabled(true);
				    int res = fc.showOpenDialog(null);
				    // We have an image!
				    try {
				        if (res == JFileChooser.APPROVE_OPTION) {
				            File[] file = fc.getSelectedFiles();
				            List<File> fImages =  Arrays.asList(file);
				            
							for(File fImage : fImages)
							{
								fImagesCollection.add(fImage);
								BufferedImage bufferedImage = ImageIO.read(fImage);
								ImageIO.write(bufferedImage, "jpg",new File("C:\\ImagePro\\oriImage_Upload\\"+fImage.getName()));
							}
							
							viewImagePanel_PictureSelect();
							btnReset.setEnabled(true);
							btnProcess.setEnabled(true);
				     
				        } // Oops!
				        else {
				            JOptionPane.showMessageDialog(null,"You must select one image to be the reference.", "Aborting...",
				                    JOptionPane.WARNING_MESSAGE);
				        }
				    } catch (Exception iOException) {
				    }

				//MyTool myTool = new MyTool();
			
			}
		});
		btnSelect.setBounds(204, 13, 145, 36);
		imageProcess.getContentPane().add(btnSelect);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 1300, 620);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(null);
		imageProcess.getContentPane().add(scrollPane);
		
		panel_1 = new JPanel();
		panel_2 = new JPanel();
		panel_1.setOpaque(true);
		panel_1.setBorder(null);
		panel_1.setBounds(5, 10, 650, 310);
		panel_2.setOpaque(true);
		panel_2.setBorder(null);
		panel_2.setBounds(5, 10, 650, 310);
		scrollPane.setViewportView(panel_1);
		scrollPane.setViewportView(panel_2);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fImagesCollection = new ArrayList<File>();
				viewImagePanel_PictureSelect();
				btnReset.setEnabled(false);
				btnProcess.setEnabled(false);
				try {
					Files.walk(Paths.get("C:/ImagePro/oriImage_Upload/"))
					.filter(Files::isRegularFile)
					.map(Path::toFile)
					.forEach(File::delete);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnReset.setBounds(380, 13, 145, 36);
		btnReset.setEnabled(false);
		imageProcess.getContentPane().add(btnReset);
		
		 btnProcess = new JButton("Process");
		 btnProcess.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {

				
				if(fImagesCollection.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please select the image.");
				}else if(!fImagesCollection.isEmpty())
				{
					Runnable run = new Runnable()
					{
						public void run()
						{
							File directoryfrom = new File("C:\\ImagePro\\oriImage_Upload");
							//File[] fList = directoryfrom.listFiles();
							//ArrayList<File> fList = (ArrayList<File>)ImageIO.read(fImagesCollection);
							
							for(File file:fImagesCollection)
							{
								long size = file.length();
								if(size !=0)
								{
									BufferedImage buffImage;
									
									try{
										
										buffImage = ImageIO.read(file);
										new ImageProcess();
										BufferedImage nImage = ImageProcess.imageDeskew(buffImage);
										ImageIO.write(nImage,"jpg",new File("C:\\ImagePro\\Result\\"+file.getName()));
										ImageIO.write(buffImage, "jpg", new File("C:\\ImagePro\\processed\\"+file.getName()));
										RImagesCollection.add(new File("C:\\ImagePro\\processed\\"+file.getName()));
										directoryfrom.delete();
									}catch(Exception ex)
									{
										System.out.print("Image operation failed");
										ex.printStackTrace();
									}
								}
							}
						}
					};
					
					 Thread runOperation = new Thread(run);
	    			 runOperation.start();
				}

			
		 	}
		 });
		 
		btnProcess.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnProcess.setBounds(557, 13, 145, 36);
		btnProcess.setEnabled(false);
		imageProcess.getContentPane().add(btnProcess);
		
		btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHome.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnHome.setBounds(34, 13, 145, 36);
		imageProcess.getContentPane().add(btnHome);
		
		 btnResult = new JButton("Show Result");
		btnResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				resultImagePanel();
			}
		});
		btnResult.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnResult.setBounds(732, 13, 145, 36);
		imageProcess.getContentPane().add(btnResult);
		btnResult.setEnabled(false);
	}
	
	/*
	 * Display Image on Jpanel Scroll [Picture Select]
	 */
	public void viewImagePanel_PictureSelect()
	{

		scrollPane.setOpaque(false);//*
		scrollPane.getViewport().setOpaque(false);//*
		scrollPane.setBorder(null);//*
		
		panel_1 = new JPanel();
		panel_1.setOpaque(false);//*
		panel_1.setBorder(null);//*
		panel_1.setLayout(grid_PictureSelect); 
		scrollPane.setViewportView(panel_1);
		
		for(File file : fImagesCollection)
		{
			ImageIcon picTempOri = new ImageIcon (file.getAbsolutePath().toString());
			
			Image img = picTempOri.getImage();
			Image newimg = img.getScaledInstance(((150*picTempOri.getIconWidth())/picTempOri.getIconHeight()), 150,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon newIcon = new ImageIcon(newimg);
			
			JButton picButton = new JButton();
			picButton.setIcon(newIcon);
			picButton.setText(file.getName().toString());
			picButton.setFont(new Font("Times New Roman", Font.BOLD, 19));//*
			picButton.setForeground(Color.BLACK);//*
			picButton.setHorizontalTextPosition(JLabel.CENTER);
			picButton.setVerticalTextPosition(JLabel.BOTTOM);
			picButton.setOpaque(false);
			picButton.setContentAreaFilled(false);
			picButton.setBorderPainted(false);
			picButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
	
					
					String testSplitarg0 = arg0.getSource().toString();
					String[] outputSplitarg0 = testSplitarg0.split("\\,");
					
					String testSplitarg0Text = outputSplitarg0[25];
					String[] outputSplitText= testSplitarg0Text.split("\\=");
					String selectedImageShortName =outputSplitText[1]; 
	
					String fullImageDirectory = ""; 
					for (int i=0 ; i<fImagesCollection.size() ; i++)
					{
						String fileDirectory = fImagesCollection.get(i).getAbsolutePath().toString();
							if(fileDirectory.contains(selectedImageShortName))
			               {
								fullImageDirectory = fileDirectory;
			               }
					}
	
					// pop-up image detail box 
					boolean deleteImg = popupDetailImageBox_PictureSelect(fullImageDirectory,selectedImageShortName);
					if (deleteImg== true)
					{
						orderDeleteImg_PictureSelect(selectedImageShortName);
					}
					
				}
			});
			panel_1.add(picButton);
			panel_1.repaint();
			panel_1.revalidate();
		}
	}
	
	public void resultImagePanel()
	{

		scrollPane.setOpaque(false);//*
		scrollPane.getViewport().setOpaque(false);//*
		scrollPane.setBorder(null);//*
		
		panel_2 = new JPanel();
		panel_2.setOpaque(false);//*
		panel_2.setBorder(null);//*
		panel_2.setLayout(grid_PictureSelect); 
		scrollPane.setViewportView(panel_2);
		
		for(File file : RImagesCollection)
		{
			ImageIcon picTempOri = new ImageIcon (file.getAbsolutePath().toString());
			
			Image img = picTempOri.getImage();
			Image newimg = img.getScaledInstance(((150*picTempOri.getIconWidth())/picTempOri.getIconHeight()), 150,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon newIcon = new ImageIcon(newimg);
			
			JButton picButton = new JButton();
			picButton.setIcon(newIcon);
			picButton.setText(file.getName().toString());
			picButton.setFont(new Font("Times New Roman", Font.BOLD, 19));//*
			picButton.setForeground(Color.BLACK);//*
			picButton.setHorizontalTextPosition(JLabel.CENTER);
			picButton.setVerticalTextPosition(JLabel.BOTTOM);
			picButton.setOpaque(false);
			picButton.setContentAreaFilled(false);
			picButton.setBorderPainted(false);
			picButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
	
					
					String testSplitarg0 = arg0.getSource().toString();
					String[] outputSplitarg0 = testSplitarg0.split("\\,");
					
					String testSplitarg0Text = outputSplitarg0[25];
					String[] outputSplitText= testSplitarg0Text.split("\\=");
					String selectedImageShortName =outputSplitText[1]; 
	
					String fullImageDirectory = ""; 
					for (int i=0 ; i<RImagesCollection.size() ; i++)
					{
						String fileDirectory = RImagesCollection.get(i).getAbsolutePath().toString();
							if(fileDirectory.contains(selectedImageShortName))
			               {
								fullImageDirectory = fileDirectory;
			               }
					}
	
					// pop-up image detail box 
					boolean deleteImg = popupDetailImageBox_PictureSelect(fullImageDirectory,selectedImageShortName);
					if (deleteImg== true)
					{
						orderDeleteImg_PictureSelect(selectedImageShortName);
					}
					
				}
			});
			panel_2.add(picButton);
			panel_2.repaint();
			panel_2.revalidate();
		}
	}
	
	/*
	 * pop-up image detail box [Picture Select]
	 */
	public boolean popupDetailImageBox_PictureSelect(String fullImageDirectory , String selectedImageShortName)
	{
		boolean deleteImage = false;
		
		String directoryImg = fullImageDirectory;
		
		ImageIcon picTempOri = new ImageIcon (directoryImg);
		Image img = picTempOri.getImage();
		Image newimg = img.getScaledInstance(((400*picTempOri.getIconWidth())/picTempOri.getIconHeight()), 500,  java.awt.Image.SCALE_SMOOTH);//Image newimg = img.getScaledInstance(400, 400,  java.awt.Image.SCALE_SMOOTH); //(Length,Height)
		ImageIcon newIcon = new ImageIcon(newimg);
		JLabel lbl = new JLabel(newIcon);
		
		JLabel lblTitle = new JLabel("<html>"+selectedImageShortName+"</html>");
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 19));
		JLabel lblDirectory = new JLabel("<html>"+directoryImg+"</html>");
		lblDirectory.setFont(new Font("Times New Roman", Font.BOLD, 19));
		
		final JPanel dataPanel = new JPanel();
		dataPanel.setOpaque(false);
		
		  dataPanel.add(lbl);
		  dataPanel.add(Box.createVerticalStrut(15));
		  JLabel lblPicture= new JLabel("<html>Picture :</html>");
		  lblPicture.setFont(new Font("Times New Roman", Font.PLAIN, 19));
	      dataPanel.add(lblPicture);
	      dataPanel.add(lblTitle);

	      dataPanel.add(Box.createVerticalStrut(15));
		  JLabel lblPath= new JLabel("<html>Path :</html>");
		  lblPath.setFont(new Font("Times New Roman", Font.PLAIN, 19));
	      dataPanel.add(lblPath);
	      dataPanel.add(lblDirectory);
	      dataPanel.add(Box.createVerticalStrut(15));
	      dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));

	    Object[] options = {"Back","Remove Picture"};
		int n = JOptionPane.showOptionDialog(
		null,
		dataPanel,
		"Picture",
		JOptionPane.YES_NO_OPTION,
		JOptionPane.PLAIN_MESSAGE,
		null,
		options,
		options[0]);

		if (n==0)
		{
			
			deleteImage = false;
		}
		else if (n==1)
		{
			
			deleteImage = true;
		}
		
		return deleteImage;
	}
	
	/*
	 * delete specific selected image [Picture Select]
	 */
	public void orderDeleteImg_PictureSelect(String deleteImgDirectory)
	{
		String selectedImageDeleteBtn = deleteImgDirectory;
		
		for (int i=0 ; i<fImagesCollection.size() ; i++)
		{
			String fileDirectory = fImagesCollection.get(i).getAbsolutePath().toString();
			
               if(fileDirectory.contains(selectedImageDeleteBtn))
               {
            	  
            	   int dialogButton = JOptionPane.YES_NO_OPTION;
            	   int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to remove picture "+selectedImageDeleteBtn+" from this clipboard?","Remove Confirmation",dialogButton);
            	   if(dialogResult == JOptionPane.YES_OPTION)
            	   {
            		   fImagesCollection.remove(i);
            		   
            		   boolean filedelete = new File("C:\\ImagePro\\oriImage_Upload\\"+selectedImageDeleteBtn).delete();
            		 
            		   viewImagePanel_PictureSelect();
            	   }
               } 
        }
	}
}
