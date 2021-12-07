#include "Matrix.h"
#include <iostream>

using namespace std;

int main() {
    
    //A - задана матриця
    int n = 0; //size matrix
    cout << "size ";
    cin >> n;
    Matrix A(n, n);
    A.entreMatrix();
    cout << "A: " << endl;
    A.print();
    A.inverseByLU();
    A.GausseJordan(A);
    cout << "result: " << endl;
    A.print();
    
    return 0;
}
