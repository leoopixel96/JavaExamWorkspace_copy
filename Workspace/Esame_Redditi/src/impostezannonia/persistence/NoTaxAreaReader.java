package impostezannonia.persistence;

import java.io.Reader;

import impostezannonia.model.TipologiaContribuente;

public interface NoTaxAreaReader {
	public TipologiaContribuente[] leggiNoTaxArea(Reader reader) throws BadFileFormatException;
}
