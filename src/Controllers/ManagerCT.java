package Controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
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
	
	public ManagerCT(ManagerUI manager){
		this.managerFrame = manager;
		client = IBookClient.getInstance();
		managerCT = this;
		UserCT.userCT.changeObserver(this,UserCT.userCT);
		managerFrame.btnThidebook.addActionListener((ActionListener)this);
		managerFrame.btnAFreezing.addActionListener((ActionListener)this);
		managerFrame.btnCpermission.addActionListener((ActionListener)this);
		managerFrame.btnUreport.addActionListener((ActionListener)this);
		managerFrame.btnUreport.addActionListener((ActionListener)this);
		managerFrame.btnBreport.addActionListener((ActionListener)this);
		managerFrame.btnPbook.addActionListener((ActionListener)this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		/*********************************************************************/
		if(e.getSource()==managerFrame.btnThidebook){
			hidebookFrame = new HideBookUI();
			hidebookFrame.btnHide.addActionListener((ActionListener)this);
			hidebookFrame.btnUnhide.addActionListener((ActionListener)this);
			hidebookFrame.btnBack.addActionListener((ActionListener)this);
			MainUI.MV.setView(hidebookFrame);
		}
		else if(hidebookFrame!=null){
			if(e.getSource()==hidebookFrame.btnBack){
				MainUI.MV.setView(managerFrame);
			}
			else if(e.getSource()==hidebookFrame.btnHide){
				HideBook(1);
			}
			else if(e.getSource()==hidebookFrame.btnUnhide){
				HideBook(0);
			}
		}
		
		/*********************************************************************/
		
		else if(e.getSource()==managerFrame.btnAFreezing){
			accountfreezFrame = new AccountFreezUI();
			accountfreezFrame.btnFreeze.addActionListener((ActionListener)this);
			accountfreezFrame.btnUnFreeze.addActionListener((ActionListener)this);
			accountfreezFrame.btnBack.addActionListener((ActionListener)this);
			MainUI.MV.setView(accountfreezFrame);
		}
		else if(accountfreezFrame!=null){
			if(e.getSource()==accountfreezFrame.btnBack){
				MainUI.MV.setView(managerFrame);
			}
			else if(e.getSource()==accountfreezFrame.btnFreeze){
				FreezeUser(1);
			}
			else if(e.getSource()==accountfreezFrame.btnUnFreeze){
				FreezeUser(0);

			}
		}
		
		/*********************************************************************/
		
		else if(e.getSource()==managerFrame.btnCpermission){
			changingpermissionFrame = new ChangingPermissionUI();
			changingpermissionFrame.btnChange.addActionListener((ActionListener)this);
			changingpermissionFrame.btnBack.addActionListener((ActionListener)this);
			MainUI.MV.setView(changingpermissionFrame);
		}
		else if(changingpermissionFrame!=null){
			if(e.getSource()==changingpermissionFrame.btnBack){
				MainUI.MV.setView(managerFrame);
			}
			else if(e.getSource()==changingpermissionFrame.btnChange){
				ChangePermission();
			}
		}
		
		/*********************************************************************/
		
		else if(e.getSource()==managerFrame.btnUreport){
			userreportFrame = new UserReportUI();
			userreportFrame.btnShowReport.addActionListener((ActionListener)this);
			userreportFrame.btnBack.addActionListener((ActionListener)this);
			MainUI.MV.setView(userreportFrame);
		}
		else if(userreportFrame!=null){
			if(e.getSource()==userreportFrame.btnBack){
				MainUI.MV.setView(managerFrame);
			}
			else if(e.getSource()==userreportFrame.btnShowReport){
				showUserReport();
			}
		}
		
		/*********************************************************************/
		
		else if(e.getSource()==managerFrame.btnBreport){
			bookreportFrame = new BookReportUI();
			bookreportFrame.btnByPurchases.addActionListener((ActionListener)this);
			bookreportFrame.btnBySearches.addActionListener((ActionListener)this);
			bookreportFrame.btnBack.addActionListener((ActionListener)this);
			MainUI.MV.setView(bookreportFrame);
		}
		else if(bookreportFrame!=null){
			if(e.getSource()==bookreportFrame.btnBack){
				MainUI.MV.setView(managerFrame);
			}
			else if(e.getSource()==bookreportFrame.btnByPurchases)
				showBookReport(0);
			else if(e.getSource()==bookreportFrame.btnBySearches)
				showBookReport(1);
		}
		
		/*********************************************************************/
		
		else if(e.getSource()==managerFrame.btnPbook){
			bookpopularityFrame = new BookPopularityUI();
			bookpopularityFrame.btnByPurchases.addActionListener((ActionListener)this);
			bookpopularityFrame.btnBySearches.addActionListener((ActionListener)this);
			bookpopularityFrame.btnBack.addActionListener((ActionListener)this);
			MainUI.MV.setView(bookpopularityFrame);
		}
		else if(bookreportFrame!=null){
			if(e.getSource()==bookreportFrame.btnBack){
				MainUI.MV.setView(managerFrame);
			}
			else if(e.getSource()==bookreportFrame.btnByPurchases)
				showBookReport(0);
			else if(e.getSource()==bookreportFrame.btnBySearches)
				showBookReport(1);
		}
		
	}

	@Override
	public void update(Observable arg0, Object obj) {

		if (obj instanceof String)
			System.out.println(obj);

		else {
			Map<String, Object> map = (HashMap<String, Object>) obj;
			Map<String, Object> returnedHash;
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
						bookreportFrame.chart = ChartFactory.createBarChart("Books histogram by Purchases","Date","Amount of purchases",(DefaultCategoryDataset)(returnedHash.get("data")),PlotOrientation.VERTICAL, false,true,false);
						bookreportFrame.p = bookreportFrame.chart.getCategoryPlot();
						bookreportFrame.p.setRangeGridlinePaint(Color.black);
						bookreportFrame.bar = new ChartPanel(bookreportFrame.chart);
						bookreportFrame.setExtraPanel();
					}
					else if((int)returnedHash.get("int") == 1){
						bookreportFrame.chart = ChartFactory.createBarChart("Books histogram by Searches","Date","Amount of searches",(DefaultCategoryDataset)(returnedHash.get("data")),PlotOrientation.VERTICAL, false,true,false);
						bookreportFrame.p = bookreportFrame.chart.getCategoryPlot();
						bookreportFrame.p.setRangeGridlinePaint(Color.black);
						bookreportFrame.bar = new ChartPanel(bookreportFrame.chart);
						bookreportFrame.setExtraPanel();
					}
					
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
	
	public void showUserReport(){
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "UserReport");
		hmap.put("obj", userreportFrame.textField.getText());

		client.handleMessageFromUI(hmap);
	}
	
	public void showBookReport(int choice){
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("op", "BookReport");
		hmap.put("choice", choice); /* 0 - By Purchases, 1 - By Searches */
		hmap.put("obj", (String) bookreportFrame.textField.getText());

		client.handleMessageFromUI(hmap);
	}
	


}
