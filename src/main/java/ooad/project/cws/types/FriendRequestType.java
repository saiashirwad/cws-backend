package ooad.project.cws.types;

public class FriendRequestType {
    private String senderName;
    private String receiverName;

	public String getSendername()
	{
		return this.senderName;
    }
    
	public String getReceivername()
	{
		return this.receiverName;
	}

	public void setSenderName(String name) {
		this.senderName = name;
	}

	public void setReceiverName(String name) {
		this.receiverName = name;
	}

}