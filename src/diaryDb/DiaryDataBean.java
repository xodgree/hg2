package diaryDb;

import java.util.Date;

// 
public class DiaryDataBean {
	
	private int num;			//일기 번호		
	private Date regdate;		//일기 쓴 날짜
	private String content;		//일기 내용
	private String imagename;	//첨부 이미지 이름
	private String emotion;		//감정 
	private String useremail;	//사용자 이메일 (일기마다 다 있어야 함.)
	private String title;		//제목

	
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
