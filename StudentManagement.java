import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JOptionPane;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentManagement implements ActionListener{

  private final long serialVersionUID=1L;
  private JFrame mainFrame;//ѧ�������������
  private JPanel top;//��������壬���ѧ����Ϣ����
  private JLabel labelTop;//��¼��|����ǩ
  private JLabel labelName;//����������ǩ
  private JTextField textName;//"����"�����
  private JLabel labelAge;//�����䡱 ��ǩ
  private JTextField textAge;;//"����"�����
  private JLabel labelGrade;//���ɼ�����ǩ
  private JTextField textGrade;//���ɼ��������
  private JButton btnAdd;//��Ӱ�ť
  
  private JPanel middle;//��ʾ����壬��ʾѧ����Ϣ����
  private JLabel labelMiddle;//����ʾ����|�� ��ǩ
  private JButton btnShowAll;//"��ʾ"��ť
  private JButton btnSortAll;//"����"��ť
  private JTextArea areaShowAll;//ѧ����Ϣ�����
  
  private JPanel bottom;//��ѯ����壬��ѯѧ����Ϣ
  private JLabel labelBottom;//"��ѯ|"��ǩ
  private JLabel labelQuery;//"������"��ǩ
  private JTextField textQuery;//"����"�����
  private JButton btnQuery;//"�ύ"��ť
  private JTextArea areaQuery;//��ѯ�����ʾ��
  
  public StudentManagement(String title){
    mainFrame=new JFrame(title);
  }
  
  public void run(){
    mainFrame.setBounds(100,100,500,250);//���ô��ڵĴ�С
    mainFrame.setVisible(true);//���ô��ڵĿɼ�
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    addInput();
    addShowAll();
    addQuery();
    
    setAction();
    
    mainFrame.validate();
    }
    
    private void addInput(){
      top=new JPanel();
      labelTop=new JLabel("¼��");
      labelName=new JLabel("����");
      labelAge=new JLabel("����");
      labelGrade=new JLabel("�ɼ�");
      textName=new JTextField(10);
      textAge=new JTextField(6);
      textGrade=new JTextField(6);
      btnAdd=new JButton("���");
      
      top.add(labelTop);
      top.add(labelName);
      top.add(textName);
      top.add(labelAge);
      top.add(textAge);
      top.add(labelGrade);
      top.add(textGrade);
      top.add(btnAdd);
      mainFrame.add(top,BorderLayout.NORTH);   
      }
      
      
       private void addShowAll(){
       	middle=new JPanel();
       	
       	labelMiddle=new JLabel("�鿴���У�");
       	btnShowAll=new JButton("��ʾ");
        btnSortAll=new JButton("����");
        areaShowAll=new JTextArea(7,25);
        
        middle.add(labelMiddle);
        middle.add(btnShowAll);
        middle.add(btnSortAll);
        middle.add(areaShowAll);
        
        mainFrame.add(middle,BorderLayout.CENTER); 
      }
      
        
      private void addQuery(){ 
      bottom=new JPanel();
      labelBottom=new JLabel("��ѯ|");
      labelQuery=new JLabel("����");
      textQuery=new JTextField(9);
      btnQuery=new JButton("�ύ");
      areaQuery=new JTextArea(1,20);
      
      
      bottom.add(labelBottom);
      bottom.add(labelQuery);
      bottom.add( textQuery);
      bottom.add(btnQuery);
      bottom.add(areaQuery);
      
      mainFrame.add(bottom,BorderLayout.SOUTH);
    }
    
    private void setAction(){
    	btnAdd.addActionListener(this);
    	btnShowAll.addActionListener(this);
    	btnSortAll.addActionListener(this);
    	btnQuery.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e){
    	String inputText=e.getActionCommand();
    	if(inputText.equals("���")){
    		addStudent();
      }else if(inputText.equals("��ʾ")){
         displayAll();
      }else if(inputText.equals("����")){
         sortAll();
      }else if(inputText.equals("�ύ")){
      	  queryStudent();
      }else {
      	  showError("error");
      }
    }
    
    private void addStudent(){
    	String name;
    	double grade;
    	int age;
    	try{
    	  name=textName.getText();
    	  age=Integer.parseInt(textAge.getText());
    	  grade=Double.parseDouble(textGrade.getText());
    	  }catch(NumberFormatException e){
    	  	showError("�����д���");
    	  	return ;
    	 }
    	 Student student=new Student(name,age,grade);
    	 
    	 StudentDAO sd=new StudentDAO();
    	 
    	 if(sd.insert(student)){
    	 	 showMsg("��ӳɹ���");
    	 	 displayAll();
    	 }else {
    	 	showError("��Ӵ���");
    	 }
    	}
    	
    	private void displayAll(){
    		StudentDAO sd=new StudentDAO();
    		StudentClass xg=sd.getStudentClass();
    		String content=DisplayUtils.display(xg.formatStudent());
    		areaShowAll.setText(content);
    }
    
    private void sortAll(){
    	StudentDAO sd=new StudentDAO();
    	StudentClass xg=sd.getStudentClass();
    	xg.sort();
    	String content=DisplayUtils.display(xg.formatStudent());
    	areaShowAll.setText(content);
    	}
    	
    	private void queryStudent(){
    		String name=textQuery.getText();
    		
    		StudentDAO sd=new StudentDAO();
    		Student student=sd.getByName(name);
    		
    		
    		if(name!=null&&name.length()>0){
    			String content=showStudent(student);
    			areaQuery.setText(content);
    	  }else{
    	     showError("��ѯ��������");
    	   }
    	}
    	
    	private String showStudent(Student student){
    		String result;
    		if(student!=null){
    			result="����"+student.getName()+"\t�ɼ�"
    			    +student.getGrade();
    		} else{
              result="ѧ�������ڣ�";
         }
         return result;
     } 
     
     
     private void showError(String errorMsg){
	 		String dialogTitle="ѧ���ɼ�����";
	 		JOptionPane.showMessageDialog(mainFrame,errorMsg,dialogTitle,JOptionPane.WARNING_MESSAGE);
	 }
	 
	 private void showMsg(String msg){
	 		String dialogTitle="ѧ���ɼ�����";
	 		JOptionPane.showMessageDialog(mainFrame,msg,dialogTitle,JOptionPane.INFORMATION_MESSAGE);
	 		
	 }
	 
}
      
   
  
