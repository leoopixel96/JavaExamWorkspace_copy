package mm.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import mm.model.Partita;

public class MyPartitaPersister implements PartitaPersister {

	@Override
	public Partita read(InputStream source) throws IOException, BadFileFormatException {
		ObjectInputStream input = new ObjectInputStream(source);
		Partita result;

		try {
			result = (Partita) input.readObject();
		} catch (Exception e) {
			throw new BadFileFormatException("partita non esistente");
		}
		
		return result;
	}

	@Override
	public void write(Partita partita, OutputStream dest) throws IOException {
		ObjectOutputStream stream = new ObjectOutputStream(dest);
		
		stream.writeObject(partita);
	}

}
