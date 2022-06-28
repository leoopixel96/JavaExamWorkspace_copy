package zs.ui;

public interface UserInteractor {
	String requestRecipientSign();
	String requestUserNotes();
	void somethingWentWrongWhileSaving(String message);
}
