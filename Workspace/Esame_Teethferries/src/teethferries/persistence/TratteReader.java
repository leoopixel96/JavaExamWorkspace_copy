package teethferries.persistence;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import teethferries.model.Tratta;

public interface TratteReader
{
	List<Tratta> leggiTratte(Reader tratteReader) throws IOException, MalformedFileException;
}
