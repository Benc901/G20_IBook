package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import Mains.IBookClient;
import Views.AccountFreezUI;
import Views.ChangingPermissionUI;
import Views.HideBookUI;
import Views.MainUI;
import Views.ManagerUI;

public class ManagerCT implements Observer, ActionListener {
	public static ManagerUI managerFrame;
	public static IBookClient client;
	public static ManagerCT managerCT;
	public static HideBookUI hidebookFrame;
	public static AccountFreezUI accountfreezFrame;
	public static ChangingPermissionUI changingpermissionFrame;
	
	public ManagerCT(ManagerUI manager){
		this.managerFrame = manager;
		client = IBookClient.getInstance();
		managerCT = this;
		UserCT.userCT.changeObserver(this,UserCT.userCT);
		managerFrame.btnThidebook.addActionListener((ActionListener)this);
		managerFrame.btnAFreezing.addActionListener((ActionListener)this);
		managerFrame.btnCpermission.addActionListener((ActionListener)this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
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

}
