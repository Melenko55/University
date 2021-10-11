#include <iostream>
#include <string>

using namespace std;

bool strIsShift(string str1, string str2) {

	int curLen = 0;

	//Якщо довжина 1 рядка не така сама як у 2, то вона не може бути циклічним зсувом
	if (str1.length() != str2.length()) {
		return false;
	}

	//Якщо продублювати 1 рядок то ми отримуємол рядок з усіма можливими зсувами
	string str3 = str1 + str1;

	for (int i = 0; i < str3.length(); i++) {
		if (str3[i] == str2[curLen]) {
			//Збільшуємо значення лічильника якщо збігаються відповідні сиволи
			++curLen;
			continue;
		} else if (curLen == str2.length()) {
			return true;
		} else if (i > str1.length() + 1 && curLen ==0) {
		//Оптимізація: якщо і більше ніж довжина 1 рядка + 1 ->, то довжина 2 рядка буде більше за кількість символів, що залишились
			return false;
		}
		//Якщо символи не співпадають обнуляємо лічильник
			curLen = 0;
	}

	//Якщо цикл не повернув true => рядок 2 не є циклічним зсувом першого
	return false;
} 


int main() {
	string s1, s2;
	
	cout << "\n\tFirst string: ";
	cin >> s1;
	cout << "\n\tString to check: ";
	cin >> s2;
	cout << "\n\t" << s2 + (strIsShift(s1, s2) ?  " is shifted " : " is not shifted ");
}

