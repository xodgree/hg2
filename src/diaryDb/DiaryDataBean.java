package diaryDb;

import java.util.Date;

// 
public class DiaryDataBean {
	
	private int num;			//�ϱ� ��ȣ		
	private Date regdate;		//�ϱ� �� ��¥
	private String content;		//�ϱ� ����
	private String imagename;	//÷�� �̹��� �̸�
	private String emotion;		//���� 
	private String useremail;	//����� �̸��� (�ϱ⸶�� �� �־�� ��.)
	private String title;		//����

	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	public String getEmotion() {
		return emotion;
	}
	public void setEmotion(String emotion) {
		this.emotion = emotion;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	@Override
	public String toString() {
		return "DiaryDataBean [num=" + num + ", regdate=" + regdate + ", content=" + content + ", imagename="
				+ imagename + ", emotion=" + emotion + ", useremail=" + useremail + ", title=" + title + "]";
	}
	

	

	
}
