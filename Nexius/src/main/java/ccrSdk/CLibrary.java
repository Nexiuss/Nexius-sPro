package ccrSdk;

import com.sun.jna.Library;
import com.sun.jna.Pointer;

public interface CLibrary extends Library {
	int GetCcrInfo(Pointer str, Pointer out, Pointer mfr);

	int OpenCcr(Pointer str);

	int CloseCcr();
}