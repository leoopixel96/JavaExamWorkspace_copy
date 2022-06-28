package zs.ui;

import java.util.Optional;

public interface UserInteractor {
	Optional<String> requestRecipientSign();
	Optional<String> requestUserNotes();
	void somethingWentWrongWhileSaving(String message);
}
