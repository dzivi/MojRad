package org.ivan.MojRad.classes;

import sun.invoke.empty.Empty;
import sun.security.util.Length;

public class Validate {

	public static boolean check(String n, String b) {
		if (b == null) {
			if (n != null && n.length() > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			if (n != null && n.length() > 0 && n.equals(b)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static boolean check(String a) {
		String b = null;
		return check(a, b);
	}

}
