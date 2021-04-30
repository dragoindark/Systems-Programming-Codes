package labOne;

import java.util.ArrayList;

public class DummyClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pwd = "burak/ahmet/ayse/";
		int occurence = pwd.indexOf("/");
		ArrayList<Integer> allIndexes = new ArrayList<Integer>();
		allIndexes.add(occurence);
		for (int i = 0; i < pwd.length(); i++) {
			occurence = pwd.indexOf("/", occurence + 1);
			if (occurence == -1) {
				break;
			} else {
				allIndexes.add(occurence);
			}
		}
		for (int value : allIndexes) {
			System.out.println(value);
		}
		ArrayList<String> subStrings = new ArrayList<String>();
		int startIndex = 0;
		for (int i = 0; i < allIndexes.size() + 1; i++) {
			if (i < allIndexes.size() && pwd.substring(startIndex, allIndexes.get(i)) != ""
					&& startIndex != pwd.length() - 1) {
				subStrings.add(pwd.substring(startIndex, allIndexes.get(i)));
			} else {
				if (i < allIndexes.size()) {
					if (pwd.length() > allIndexes.get(i)) {
						String addPwd = pwd.substring(startIndex, pwd.length());
						if (!addPwd.equals("")) {
							subStrings.add(addPwd);
						}

					}
				} else {
					if (pwd.length() > allIndexes.get(i - 1)) {
						String addPwd = pwd.substring(startIndex, pwd.length());
						if (!addPwd.equals("")) {
							subStrings.add(addPwd);
						}
					}
				}
			}
			try {
				startIndex = allIndexes.get(i) + 1;
			} catch (IndexOutOfBoundsException e) {
				break;
			}
			System.out.println("New start index is " + startIndex);
		}
		String endString="";
		for (int i = 0; i < allIndexes.size(); i++) {
			if (allIndexes.get(i) == 0 && allIndexes.get(allIndexes.size() - 1) == pwd.length() - 1) {
				for (int k = 0; k < subStrings.size(); k++) {
					if (k == 0) {
						endString+="//"+subStrings.get(k);
					}else if(k==subStrings.size()-1) {
						endString+=subStrings.get(k)+"//";
					}
				}
			}

		}
		for (String str : subStrings) {
			System.out.println(str);
		}

	}

}
