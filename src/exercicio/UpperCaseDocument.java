package exercicio;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class UpperCaseDocument extends PlainDocument { 
    @Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (str == null) {
            return;
        }
        super.insertString(offs, str.toUpperCase(), a);
    }
}
