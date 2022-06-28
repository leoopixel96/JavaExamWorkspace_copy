package teethferries.persistence;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import teethferries.model.Servizio;
import teethferries.model.Tratta;

public interface ServiziReader
{
	List<Servizio> leggiServizi(Reader reader, List<Tratta> tratteMap) 
			throws IOException, MalformedFileException;
}
