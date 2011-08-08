package dayton.clickatell.smsTest;

public class MessageBean {
	String cellNumber;
	String messageTest;
	
	
	public MessageBean(String cellNumber, String messageText) {
		this.cellNumber = cellNumber;
		this.messageTest = messageText;
	}
	
	public String getCellNumber() {
		return cellNumber;
	}
	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}
	public String getMessageTest() {
		return messageTest;
	}
	public void setMessageTest(String messageTest) {
		this.messageTest = messageTest;
	}

}
