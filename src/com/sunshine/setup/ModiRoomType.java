/**
 *##############################################################################
 *
 *	[ 项目名      ]  : 阳光酒店管理系统
 *  [ 公司名      ]  : 清华IT
 *	[ 模块名      ]  : 修改客房类型
 *	[ 文件名      ]  : ModiRoomType.java
 *	[ 相关文件    ]  : 
 *	[ 文件实现功能]  : 修改新客房类型的设置
 *	[ 作者        ]  : 董丰
 *	[ 版本        ]  : 1.1
 *	----------------------------------------------------------------------------
 *	[ 备注        ]  : 
 *	----------------------------------------------------------------------------
 *	[ 修改记录    ]  : 
 *
 *	[ 日  期 ]     [版本]         [修改人]         [修改内容] 
 *	2006/04/19      1.0             董丰            创建
 *	2006/04/20      1.1             顾俊            实现数据修改保存
 *	##--------------------------------------------------------------------------
 *  			 版权所有(c) 2006-2007,  SunshineSOFT Corporation
 *	--------------------------------------------------------------------------##
 *	
 *	[ 函数说明    ]  :	
 *	
 *	[## public ModiRoomType(JDialog dialog) {} ]:
 *		功能: 修改客房类型
 *
 *	[## private void buildPC() {} ]:
 *		功能: 制作房间类型面板
 *
 *	[## private boolean isValidity() {} ]:
 *		功能: 测试用户输入的数据是否合法
 *
 *	[## private void saveRoomType() {} ]:保存数据
 *		功能: 保存数据
 *
 *  [ 遗留问题    ]  : 
 *
 *##############################################################################
 */
package com.sunshine.setup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.sunshine.sunsdk.sql.*;			//公共类库
import com.sunshine.sunsdk.swing.*;
import com.sunshine.sunsdk.system.*;
import com.sunshine.mainframe.HotelFrame;	//主框架窗口


public class ModiRoomType 
extends JDialog 
implements ActionListener {
	
	public static JTextField tf0, tf1, tf2, tf3, tf4, tf5;
	public static JCheckBox chk;
	private JButton bt1, bt2;
	private JPanel panelMain, pc, ps;
	
	
	
	/**=======================================================================**
	 *		[## public ModiRoomType(JDialog dialog) {} ]: 		构造函数
	 *			参数   ：JDialog对象表示本对话框的父窗口
	 *			返回值 ：无
	 *			修饰符 ：public
	 *			功能   ：添加新的客房类型
	 **=======================================================================**
	 */
	public ModiRoomType(JDialog dialog) {
		super(dialog,"修改房间类型参数",true);
		
		bt1 = new TJButton ("pic/save.gif", " 保  存 ", "保存房间类型"); 
		bt2 = new TJButton ("pic/cancel.gif", " 取  消 ", "放弃当前操作"); 
		
		JLabel lb = new JLabel();			//假空格
		panelMain = new JPanel(new BorderLayout(0, 0));
		pc 		  = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
		ps		  = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 6));
		
		//加入组件
		ps.add(bt1);
		ps.add(bt2);
		
		buildPC();							//制作房间类型面板
		
		//加入主面板
		panelMain.add("North", lb);
		panelMain.add("Center",pc);
		panelMain.add("South", ps);
		
		//加事件监听
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		tf0.addActionListener(this);		//为文本框加监听
		tf1.addActionListener(this);
		tf2.addActionListener(this);
		tf3.addActionListener(this);
		tf4.addActionListener(this);
		tf5.addActionListener(this);
		chk.addActionListener(this);
		
		this.setContentPane(panelMain);
		this.setPreferredSize(new Dimension(300, 305));
		this.setMinimumSize(new Dimension(300, 305));
		this.setResizable(false);			//不允许改变窗口大小
		pack();
		sunswing.setWindowCenter(this);		//窗口屏幕居中
	}
	
	/**=======================================================================**
	 *		[## private void buildPC() {} ]: 		制作房间类型面板
	 *			参数   ：无
	 *			返回值 ：无
	 *			修饰符 ：private
	 *			功能   ：制作房间类型面板
	 **=======================================================================**
	 */
	private void buildPC() {
		JPanel pc1, pc2, ck;
		JLabel lb0, lb1, lb2, lb3, lb4, lb5, lb6, lb7;
		
		pc1 = new JPanel(new GridLayout(6, 1, 0, 10));		//定义面板
		pc2 = new JPanel(new GridLayout(6, 1, 0,  7));
		ck	= new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
		
		lb0 = new JLabel("类型编号：");
		lb1 = new JLabel("房间类型：");
		lb2 = new JLabel("床位数量：");
		lb3 = new JLabel("预设单价：");
		lb4 = new JLabel("预设押金：");
		lb5 = new JLabel("钟点计费：");
		lb6 = new JLabel("/天");
		lb7 = new JLabel("/小时");
		
		chk = new JCheckBox("　允许开钟点房　　　　　　　");
		
		tf0 = new TJTextField(10);
		tf1 = new TJTextField(10);
		tf2 = new TJTextField("1", 10);
		tf3 = new TJMoneyField();
		tf4 = new TJMoneyField();
		tf5 = new TJMoneyField();
		
		tf0.setEnabled(false);
		tf2.setHorizontalAlignment(JTextField.RIGHT);
		
		//加入组件
		pc1.add(lb0);
		pc1.add(lb1);
		pc1.add(lb2);
		pc1.add(lb3);
		pc1.add(lb4);
		pc1.add(lb5);
		
		pc2.add(tf0);
		pc2.add(tf1);
		pc2.add(tf2);
		pc2.add(tf3);
		pc2.add(tf4);
		pc2.add(tf5);
		
		ck.add(chk);
		
		pc.add(pc1);
		pc.add(pc2);
		pc.add(ck);
		
		pc.setBorder (BorderFactory.createTitledBorder("当前房间类型"));
	}
	
	/**=======================================================================**
	 *		[## private boolean isValidity() {} ]: 	测试用户输入的数据是否合法
	 *			参数   ：无
	 *			返回值 ：boolean
	 *			修饰符 ：private
	 *			功能   ：测试用户输入的数据是否合法
	 **=======================================================================**
	 */
	private boolean isValidity() {
		if(tf1.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "房间 [ 类型名称 ] 不能为空", "提示", 
			JOptionPane.INFORMATION_MESSAGE);
			tf1.requestFocus(true);
			return false;
		}else if(!suntools.isNum(tf2.getText(), 2, 1, 10)) {
			JOptionPane.showMessageDialog(null, "[ 床位数量 ] 只能是数字，且范围在 1-10 之间", 
			"提示", JOptionPane.INFORMATION_MESSAGE);
			tf2.requestFocus(true);
			return false;
		}else if(Double.parseDouble(tf3.getText()) >= Double.parseDouble(tf4.getText())) {
			JOptionPane.showMessageDialog(null, " [ 预设押金 ] 必需要大于 [ 预设单价 ]", 
			"提示", JOptionPane.INFORMATION_MESSAGE);
			tf2.requestFocus(true);
			return false;
		}//Endif
		return true;
	}
	
	/**=======================================================================**
	 *		[## private void saveRoomType() {} ]: 	保存数据
	 *			参数   ：无
	 *			返回值 ：无
	 *			修饰符 ：private
	 *			功能   ：保存数据
	 **=======================================================================**
	 */
	private void saveRoomType() {
		if(isValidity()) {					//检测用户输入的数据是否合法
			try {
				ResultSet rs = sunsql.executeQuery("select r_type from roomtype " +
				"where delmark=0 and id!='" + tf0.getText() + "' and r_type='" +
				 tf1.getText() + "'");
				
				if(rs.next()) {				//检测新的类型编号是否存在
					JOptionPane.showMessageDialog(null, "新的房间类型名称 [ " + tf1.getText() +
					" ] 已存在，不能执行修改操作，请重新核对 ...", "提示", JOptionPane.INFORMATION_MESSAGE);
					tf1.requestFocus(true);
					return;
				}//Endif
				String flag = "N";			//获得是否可钟点计费
				if(chk.isSelected()) {
					flag = "Y";
				}//Endif
				
				String sqlCode[] = new String[2];
				
				sqlCode[0] = "update roomtype set r_type='" + tf1.getText() + 
				"',bed=" + tf2.getText() + ",price=" + tf3.getText() + ",foregift=" +
				tf4.getText() + ",cl_room='" + flag + "',cl_price=" + tf5.getText() +
				" where delmark=0 and id='" + 	tf0.getText() + "'";
				
				sqlCode[1] = "update customertype set price=" + tf3.getText() + 
				" where delmark=0 and dis_attr='" + tf0.getText() + "'";
				
				int rec = sunsql.runTransaction(sqlCode);			//将数据保存到数据库
				if(rec < sqlCode.length) {
					JOptionPane.showMessageDialog(null, "保存新的房间类型信息失败，" +
					"请检查网络连接或联系管理员", "错误", JOptionPane.ERROR_MESSAGE);
					return;					//用户继续输入
				}//Endif
				String journal = "修改了房间类型设置-- [ " + tf1.getText() + " ]";
				Journal.writeJournalInfo(HotelFrame.userid, journal, Journal.TYPE_RT);//记录操作日志
				this.setVisible(false);
		    }
		    catch (Exception ex) {
		    	System.out.println ("ModiRoomType false");
		    }
		}//End try
	}//End saveRoomType
	
	
	/**=======================================================================**
	 *			ActionListener 监听
	 **=======================================================================**
	 */
	public void actionPerformed(ActionEvent ae) {
		Object o = ae.getSource();
		if(o == bt1) {					//保存类型
			saveRoomType();
		}else if(o == bt2) {			//取消操作
			this.setVisible(false);
		}else if(o == tf0) {
			tf1.requestFocus(true);
		}else if(o == tf1) {
			tf2.requestFocus(true);
		}else if(o == tf2) {
			tf3.requestFocus(true);
		}else if(o == tf3) {
			tf4.requestFocus(true);
		}else if(o == tf4) {
			if(tf5.isEnabled()) {		//如果允许钟点房，则可输入价格
				tf5.requestFocus(true);
			}else {
				saveRoomType();			//保存类型
			}
		}else if(o == tf5) {
			saveRoomType();				//保存类型
		}else if(o == chk) {
			if(chk.isSelected()) {
				tf5.setEnabled(true);
				tf5.requestFocus(true);
			}else {
				tf5.setEnabled(false);
				tf1.requestFocus(true);
			}//Endif
			tf5.setText("0");
		}//Endif
	}//End actionPerformed
}