package Controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import Entities.BookET;
import Mains.IBookClient;
import Views.AccountFreezUI;
import Views.BookPopularityUI;
import Views.BookReportUI;
import Views.ChangingPermissionUI;
import Views.HideBookUI;
import Views.MainUI;
import Views.ManagerUI;
import Views.UserReportUI;
import graphics.GUIimagejpg;

public class ManagerCT implements Observer, ActionListener {
	public static ManagerUI managerFrame;
	public static IBookClient client;
	public static ManagerCT managerCT;
	public static HideBookUI hidebookFrame;
	public static AccountFreezUI accountfreezFrame;
	public static ChangingPermissionUI changingpermissionFrame;
	public static UserReportUI userreportFrame;
	public static BookReportUI bookreportFrame;
	public static BookPopularityUI bookpopularityFrame;
	public static Map<String, Object> returnedHash;
	
	public ManagerCT(ManagerUI manager){
		this.managerFrame = manager;
		client = IBookClient.getInstance();
		managerCT = this;
		UserCT.userCT.changeObserver(this,UserCT.userCT);
		managerFrame.btnThidebook.addActionListener((ActionListener)this);
		managerFrame.btnAFreezing.addActionListener((ActionListener)this);
		managerFrame.btnCpermission.addActionListener((ActionListener)this);
		managerFrame.btnUreport.addActionListener((ActionListener)this);
		managerFrame.btnBreport.addActionListener((ActionListener)this);
		managerFrame.btnPbook.addActionListener((ActionListener)this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		/*******************************Hide Book**************************************/
		if(e.getSource()==managerFrame.btnThidebook){
			hidebookFrame = new HideBookUI();
			hidebookFrame.btnHide.addActionListener((ActionListener)this);
			hidebookFrame.btnUnhide.addActionListener((ActionListener)this);
			hidebookFrame.btnBack.addActionListener((ActionListener)this);
			MainUI.MV.setView(hidebookFrame);
		}
		if(hidebookFrame!=null){
			if(e.getSource()==hidebookFrame.btnBack){
				MainUI.MV.setView(managerFrame);
			}
			else if(e.getSource()==hidebookFrame.btnHide){
				if(!ifContainOnlyNum(hidebookFrame.textField.getText()))
					JOptionPane.showMessageDialog(null,"Please enter valid variables");	
				else
					HideBook(1);
			}
			else if(e.getSource()==hidebookFrame.btnUnhide){
				if(!ifContainOnlyNum(hidebookFrame.textField.getText()))
					JOptionPane.showMessageDialog(null,"Please enter valid variables");	
				else
					HideBook(0);
			}
		}
		
		/*******************************Account Freeze**************************************/
		
		if(e.getSource()==managerFrame.btnAFreezing){
			accountfreezFrame = new AccountFreezUI();
			accountfreezFrame.btnFreeze.addActionListener((ActionListener)this);
			accountfreezFrame.btnUnFreeze.addActionListener((ActionListener)this);
			accountfreezFrame.btnBack.addActionListener((ActionListener)this);
			MainUI.MV.setView(accountfreezFrame);
		}
		if(accountfreezFrame!=null){
			if(e.getSource()==accountfreezFrame.btnBack){
				MainUI.MV.setView(managerFrame);
			}
			else if(e.getSource()==accountfreezFrame.btnFreeze){
				if(!ifContainOnlyNum(accountfreezFrame.textField.getText()))
					JOptionPane.showMessageDialog(null,"Please enter valid variables");	
				else
					FreezeUser(1);
			}
			else if(e.getSource()==accountfreezFrame.btnUnFreeze){
				if(!ifContainOnlyNum(accountfreezFrame.textField.getText()))
					JOptionPane.showMessageDialog(null,"Please enter valid variables");	
				else
					FreezeUser(0);
			}
		}
		
		/********************************Changing Permission*************************************/
		
		if(e.getSource()==managerFrame.btnCpermission){
			changingpermissionFrame = new ChangingPermissionUI();
			changingpermissionFrame.btnChange.addActionListener((ActionListener)this);
			changingpermissionFrame.btnBack.addActionListener((ActionListener)this);
			MainUI.MV.setView(changingpermissionFrame);
		}
		if(changingpermissionFrame!=null){
			if(e.getSource()==changingpermissionFrame.btnBack){
				MainUI.MV.setView(managerFrame);
			}
			else if(e.getSource()==changingpermissionFrame.btnChange){
				ChangePermission();
			}
		}
		
		/********************************User Report*************************************/
		
		if(e.getSource()==managerFrame.btnUreport){
			userreportFrame = new UserReportUI();
			userreportFrame.btnShowReport.addActionListener((ActionListener)this);
			userreportFrame.btnBack.addActionListener((ActionListener)this);
			MainUI.MV.setView(userreportFrame);
		}
		if(userreportFrame!=null){
			if(e.getSource()==userreportFrame.btnBack){
				MainUI.MV.setView(managerFrame);
			}
			else if(e.getSource()==userreportFrame.btnShowReport){
				if(!ifContainOnlyNum(userreportFrame.textField.getText()))
					JOptionPane.showMessageDialog(null,"Please enter valid User ID");
				else if(!validDate(userreportFrame.ddFrom.getText(),userreportFrame.MMFrom.getText(),userreportFrame.yyFrom.getText()))
					JOptionPane.showMessageDialog(null,"Please enter valid start date (dd/mm/yyyy) !");
				else if(!validDate(userreportFrame.ddTo.getText(),userreportFrame.MMTo.getText(),userreportFrame.yyTo.getText()))
					JOptionPane.showMessageDialog(null,"Please enter valid end date (dd/mm/yyyy) !");
				else
					showUserReport(makeString(userreportFrame.ddFrom.getText(),userreportFrame.MMFrom.getText(),userreportFrame.yyFrom.getText()),
							makeString(userreportFrame.ddTo.getText(),userreportFrame.MMTo.getText(),userreportFrame.yyTo.getText()));
			}

		}
		
		
		/********************************Book Report*************************************/
							/* 0 - By Purchases, 1 - By Searches */
		if(e.getSource()==managerFrame.btnBreport){ 
			bookreportFrame = new BookReportUI();
			bookreportFrame.btnByPurchases.addActionListener((ActionListener)this);
			bookreportFrame.btnBySearches.addActionListener((ActionListener)this);
			bookreportFrame.btnBack.addActionListener((ActionListener)this);
			MainUI.MV.setView(bookreportFrame);
		}
		if(bookreportFrame!=null){
			if(e.getSource()==bookreportFrame.btnBack){
				MainUI.MV.setView(managerFrame);
			}
			else if(e.getSource()==bookreportFrame.btnByPurchases){
				if(!ifContainOnlyNum(bookreportFrame.textField.getText()))
					JOptionPane.showMessageDialog(null,"Please enter valid ID");
				else if(!validDate(bookreportFrame.ddFrom.getText(),bookreportFrame.MMFrom.getText(),bookreportFrame.yyFrom.getText()))
					JOptionPane.showMessageDialog(null,"Please enter valid start date (dd/mm/yyyy) !");
				else if(!validDate(bookreportFrame.ddTo.getText(),bookreportFrame.MMTo.getText(),bookreportFrame.yyTo.getText()))
					JOptionPane.showMessageDialog(null,"Please enter valid end date (dd/mm/yyyy) !");
				else
					showBookReport(0,makeString(bookreportFrame.ddFrom.getText(),bookreportFrame.MMFrom.getText(),bookreportFrame.yyFrom.getText()),
							makeString(bookreportFrame.ddTo.getText(),bookreportFrame.MMTo.getText(),bookreportFrame.yyTo.getText()));
			}
			else if(e.getSource()==bookreportFrame.btnBySearches){
				if(!ifContainOnlyNum(bookreportFrame.textField.getText()))
					JOptionPane.showMessageDialog(null,"Please enter valid ID");
				else if(!validDate(bookreportFrame.ddFrom.getText(),bookreportFrame.MMFrom.getText(),bookreportFrame.yyFrom.getText()))
					JOptionPane.showMessageDialog(null,"Please enter valid start date (dd/mm/yyyy) !");
				else if(!validDate(bookreportFrame.ddTo.getText(),bookreportFrame.MMTo.getText(),bookreportFrame.yyTo.getText()))
					JOptionPane.showMessageDialog(null,"Please enter valid end date (dd/mm/yyyy) !");
				else
					showBookReport(1,makeString(bookreportFrame.ddFrom.getText(),bookreportFrame.MMFrom.getText(),bookreportFrame.yyFrom.getText()),
							makeString(bookreportFrame.ddTo.getText(),bookreportFrame.MMTo.getText(),bookreportFrame.yyTo.getText()));
			}
		}
		
		/******************************Book Rank***************************************/
	
		if(e.getSource()==managerFrame.btnPbook){
			bookpopularityFrame = new BookPopularityUI();
			bookpopularityFrame.btnTotalRank.addActionListener((ActionListener)this);
			bookpopularityFrame.btnGenreRank.addActionListener((ActionListener)this);
			bookpopularityFrame.btnBack.addActionListener((ActionListener)this);
			bookpopularityFrame.comboBox.addActionListener((ActionListener)this);
			bookpopularityFrame.btnVButton.addActionListener((ActionListener)this);
			MainUI.MV.setView(bookpopularityFrame);
		}
		if(bookpopularityFrame!=null){
			if(e.getSource()==bookpopularityFrame.btnBack){
				MainUI.MV.setView(managerFrame);
			}
			else if(e.getSource()==bookpopularityFrame.btnTotalRank){
				bookpopularityFrame.profile.setIcon(null);
				bookpopularityFrame.lblNewBookName.setText("");
				bookpopularityFrame.lblAuthor.setText("");
				bookpopularityFrame.lblGenere.setText("");
				bookpopularityFrame.btnVButton.setText("");
				bookpopularityFrame.btnVButton.setOpaque(false);
				bookpopularityFrame.btnVButton.setContentAreaFilled(false);
				bookpopularityFrame.btnVButton.setBorderPainted(false);
				bookpopularityFrame.comboBox.removeAllItems();
				bookpopularityFrame.comboBox.setEnabled(false);
				if(!ifContainOnlyNum(bookpopularityFrame.textField.getText()))
					JOptionPane.showMessageDialog(null,"Please enter valid variables");	
				else
					showBookRank(0);// Total rank	
			}
			else if(e.getSource()==bookpopularityFrame.btnGenreRank){
				bookpopularityFrame.profile.setIcon(null);
				bookpopularityFrame.lblNewBookName.setText("");
				bookpopularityFrame.lblAuthor.setText("");
				bookpopularityFrame.lblGenere.setText("");
				bookpopularityFrame.lblRank.setText("");
				bookpopularityFrame.comboBox.setEnabled(true);
				if(!ifContainOnlyNum(bookpopularityFrame.textField.getText()))
					JOptionPane.showMessageDialog(null,"Please enter valid variables");	
				else
					updateGeneresInComboBox(Integer.parseInt((String)bookpopularityFrame.textField.getText()));
			}
			else if(e.getSource()==bookpopularityFrame.btnVButton){
					showBookRank(1);// Genre rank	
			}
		}
		
	}

	@Override
	public void update(Observable arg0, Object obj) {

		if (obj instanceof String)
			System.out.println(obj);

		else {
			Map<String, Object> map = (HashMap<String, Object>) obj;
			String op = (String) map.get("op");
			
			// what operation was made in the server and how to respond.
			switch (op) {
			case "HideBook":
				if((int)map.get("obj")==0){
					JOptionPane.showMessageDialog(null,"ID is inncorrect,\n Please inser valid book id");
				}else if((int)map.get("obj")==1){
					JOptionPane.showMessageDialog(null,"Successfull Hide book!");
					MainUI.MV.setView(managerFrame);
				}else if((int)map.get("obj")==2){
					JOptionPane.showMessageDialog(null,"The book is hidden already !");
					MainUI.MV.setView(managerFrame);
				}else if((int)map.get("obj")==3){
					JOptionPane.showMessageDialog(null,"The book is unHidden already !");
					MainUI.MV.setView(managerFrame);
				}else if((int)map.get("obj")==4){
					JOptionPane.showMessageDialog(null,"Successfull unHidde book !");
					MainUI.MV.setView(managerFrame);
				}
				break;
				
			case "FreezeUser":
				if((int)map.get("obj")==0){
					JOptionPane.showMessageDialog(null,"ID is inncorrect,\n Please inser valid user id");	
				}else if((int)map.get("obj")==1){
					JOptionPane.showMessageDialog(null,"Successfull Freeze User!");
					MainUI.MV.setView(managerFrame);
				}else if((int)map.get("obj")==2){
					JOptionPane.showMessageDialog(null,"The user is frozen already!");
					MainUI.MV.setView(managerFrame);
				}else if((int)map.get("obj")==3){
					JOptionPane.showMessageDialog(null,"The user is unFrozen already!");
					MainUI.MV.setView(managerFrame);
				}else if((int)map.get("obj")==4){
					JOptionPane.showMessageDialog(null,"Successfull unFrozen The User!");
					MainUI.MV.setView(managerFrame);
				}
				break;
				
			case "ChangePermission":
				if((int)map.get("obj")==0){
					JOptionPane.showMessageDialog(null,"ID is inncorrect,\n Please inser valid user id");	
				}else if((int)map.get("obj")==1){
					JOptionPane.showMessageDialog(null,"User permissions changed successfully !");
					MainUI.MV.setView(managerFrame);
				}
				break;
				
			case "UserReport":
				
					if(map.get("obj") == null){
						if (userreportFrame.model.getRowCount() > 0) {
		                    for (int i = userreportFrame.model.getRowCount() - 1; i > -1; i--)
		                    	userreportFrame.model.removeRow(i);
		                }
						JOptionPane.showMessageDialog(null,"The user does not purchased books yet");	
					}
				
				if (userreportFrame.model.getRowCount() > 0) {
                    for (int i = userreportFrame.model.getRowCount() - 1; i > -1; i--) {
                    	userreportFrame.model.removeRow(i);
                    }
                }
				if((ArrayList<BookET>)map.get("obj") instanceof ArrayList<?>){
					ArrayList<BookET> books = (ArrayList<BookET>)map.get("obj");
					for(int i=0 ; i<books.size(); i++){
						userreportFrame.model.addRow(new Object[] {
							books.get(i).getBID(),books.get(i).getBTitle(),
							books.get(i).getbPurchaseDate()});
					}
				}
				break;
				
			case "BookReport":
				if(map.get("obj") instanceof Integer){
					if((int)map.get("obj") == 0)
						JOptionPane.showMessageDialog(null,"The book has not purchased yet ! ");
					else if((int)map.get("obj") == 1)
						JOptionPane.showMessageDialog(null,"The book has not searched yet ! ");
					else if((int)map.get("obj") == 2)
						JOptionPane.showMessageDialog(null,"Insert valid book ID please.");	
				}
				else if(map.get("obj") instanceof HashMap){
					returnedHash = (HashMap<String, Object>) map.get("obj");
					if((int)returnedHash.get("int") == 0){
						bookreportFrame.chart = ChartFactory.createBarChart3D("Books report, by Purchases","Date","Amount of purchases",(DefaultCategoryDataset)(returnedHash.get("data")),PlotOrientation.VERTICAL, false,true,false);
						bookreportFrame.chart.setBackgroundPaint(new Color(230, 230, 250));
						bookreportFrame.chart.getTitle().setPaint(Color.red);
						bookreportFrame.p = bookreportFrame.chart.getCategoryPlot();
						bookreportFrame.p.setRangeGridlinePaint(Color.black);
						bookreportFrame.domainAxis = bookreportFrame.p.getDomainAxis();
						bookreportFrame.domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));
						bookreportFrame.bar = new ChartPanel(bookreportFrame.chart);
						bookreportFrame.setExtraPanel();
					}
					else if((int)returnedHash.get("int") == 1){
						bookreportFrame.chart = ChartFactory.createBarChart3D("Books report, by Searches","Date","Amount of searches",(DefaultCategoryDataset)(returnedHash.get("data")),PlotOrientation.VERTICAL, false,true,false);
						bookreportFrame.chart.setBackgroundPaint(new Color(230, 230, 250));
						bookreportFrame.chart.getTitle().setPaint(Color.red);
						bookreportFrame.p = bookreportFrame.chart.getCategoryPlot();
						bookreportFrame.p.setRangeGridlinePaint(Color.black);
						bookreportFrame.domainAxis = bookreportFrame.p.getDomainAxis();
						bookreportFrame.domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));
						bookreportFrame.bar = new ChartPanel(bookreportFrame.chart);
						bookreportFrame.setExtraPanel();
					}
					
				}
				break;
				
				
			case "updateGeneresInComboBox":
				
				if((returnedHash = (HashMap<String, Object>) map.get("obj")) == null)
					JOptionPane.showMessageDialog(null,"The book does not exist !");	
				else{
					String[] tempString = (String[])returnedHash.get("genereNameArr");
					bookpopularityFrame.comboBox.removeAllItems();
					for(int i = 0 ; i < tempString.length ; i++)
						bookpopularityFrame.comboBox.addItem(tempString[i]);
					bookpopularityFrame.btnVButton.setText("Ok");
					bookpopularityFrame.btnVButton.setOpaque(true);
					bookpopularityFrame.btnVButton.setContentAreaFilled(true);
					bookpopularityFrame.btnVButton.setBorderPainted(true);
				}
			break;
				
			case "BookRank":
				
				if(map.get("obj") == null)
					JOptionPane.showMessageDialog(null,"The book does not exist !");	
				else{
					BookET temp = (BookET)map.get("obj");
					bookpopularityFrame.profile.setIcon(new GUIimagejpg("/books/" +temp.getBPhoto(),bookpopularityFrame.profile.getWidth(),bookpopularityFrame.profile.getHeight()).image);
					bookpopularityFrame.lblNewBookName.setText("Book Name : " + temp.getBTitle());
					bookpopularityFrame.lblAuthor.setText("Author : "+ temp.getBAuthor());
					bookpopularityFrame.lblGenere.setText("Genere : " + temp.getBGenre());
					if(temp.getBGenreRank() == 0)
						bookpopularityFrame.lblRank.setText("Total Rank : " + temp.getBTotalRank());
					else
						bookpopularityFrame.lblRank.setText("Books rank in requested genre " + "\"" + (String)bookpopularityFrame.comboBox.getSelectedItem() + "\"" + " : " + temp.getBGenreRank());
				}
			break;
		
			}	
		}
	}
	
	public void HideBook(int choice){
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "HideBook");
		hmap.put("hide", choice);
		hmap.put("obj", hidebookFrame.textField.getText());

		client.handleMessageFromUI(hmap);
	}
	
	public void FreezeUser(int choice){
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "FreezeUser");
		hmap.put("freeze", choice);
		hmap.put("obj", accountfreezFrame.textField.getText());

		client.handleMessageFromUI(hmap);
	}
	
	public void ChangePermission(){
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "ChangePermission");
		hmap.put("obj", changingpermissionFrame.textField.getText());
		hmap.put("newPer", Integer.parseInt((String) changingpermissionFrame.comboBox.getSelectedItem()));

		client.handleMessageFromUI(hmap);
		
	}
	
	public void showUserReport(String fromDate, String toDate){
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "UserReport");
		hmap.put("obj", userreportFrame.textField.getText());
		hmap.put("from", fromDate);
		hmap.put("to", toDate);
		
		client.handleMessageFromUI(hmap);
	}
	
	public void showBookReport(int choice, String fromDate, String toDate){
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "BookReport");
		hmap.put("choice", choice); /* 0 - By Purchases, 1 - By Searches */
		hmap.put("obj", (String) bookreportFrame.textField.getText());
		hmap.put("from", fromDate);
		hmap.put("to", toDate);

		client.handleMessageFromUI(hmap);
	}
	
	public void updateGeneresInComboBox(int bId){
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "updateGeneresInComboBox");
		hmap.put("BId", bId); /* Book ID */
		
		client.handleMessageFromUI(hmap);
	}
	
	public void showBookRank(int choice){
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "BookRank");
		hmap.put("choice", choice); /* 0 - Total Rank, 1 - Genre Rank */
		if(choice == 1){
			hmap.put("genre", (String)bookpopularityFrame.comboBox.getSelectedItem());
			hmap.put("genreArrList", returnedHash);
		}
		hmap.put("obj", (String) bookpopularityFrame.textField.getText());

		client.handleMessageFromUI(hmap);
	}

	public boolean ifContainOnlyNum(String str){
		return (str.matches("[0-9]+"));
	}
	
	public boolean ifContainOnlyNum(String dd, String MM, String yyyy){
		return (dd.matches("[0-9]+") && MM.matches("[0-9]+") && yyyy.matches("[0-9]+"));
	}
	
	public boolean validDate(String dd, String MM, String yyyy){
		return (dd.matches("[0-9]+") && dd.length()==2 && MM.matches("[0-9]+") && MM.length()==2 && yyyy.matches("[0-9]+") && yyyy.length()==4);
	}
	
	public String makeString(String dd, String MM, String yyyy){
		return yyyy + "-" + MM + "-" + dd;
	}

}
