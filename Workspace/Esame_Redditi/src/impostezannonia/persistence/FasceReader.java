package impostezannonia.persistence;

import java.io.Reader;
import java.util.List;

import impostezannonia.model.Fascia;

public interface FasceReader {
	public List<Fascia> leggiFasceReddito(Reader reader) throws BadFileFormatException;
}
