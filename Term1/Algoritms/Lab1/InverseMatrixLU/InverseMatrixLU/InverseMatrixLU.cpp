#include <iostream>
#include <vector>

using namespace std;


void inverseByLU(vector<vector<double>>, int, int);
void inverseMatrix(vector<vector<double>>, int, int);
ostream& operator<<(ostream&, vector<double>);
ostream& operator<<(ostream&, vector<vector<double>>);


int main() {
    vector<vector<double>> A{ {2, 4, 3, 5}, {-4, -7, -5, -8}, {6, 8, 2, 9}, {4, 9, -2, 14}};
    cout << "Matrix A:\n" << A << endl;
    inverseByLU(A, 4, 4);
    
}

ostream& operator<<(ostream& os, vector<double> matrix) {
    for (double d : matrix) {
        os << d << " ";
    }
    os << "\n";
    return os;
}
ostream& operator<<(ostream& os, vector<vector<double>> matrix) {
    for (vector<double> row : matrix) {
        os << row;
    }
    return os;
}

void inverseByLU(vector<vector<double>> A, int Rows, int Cols) {

    vector<vector<double>> L( Rows, vector<double>(Cols, 0) );
    vector<vector<double>> U( Rows, vector<double>(Cols, 0) );


    for (int i = 0; i < Rows; i++) {
        for (int j = 0; j < Cols; j++) {
            if (i == j) {
                L[i][i] = 1;
                U[i][j] = 0;
            }
            else {
                L[i][j] = 0;
                U[i][j] = 0;
            }
        }
    }

    
    for (int i = 0; i < Rows; i++) {
        
        for (int j = i + 1; j < Cols; j++) {
            double pivot = A[j][i] / A[i][i];
            L[j][i] = pivot;
            for (int k = 0; k < Cols; k++) {
                A[j][k] -= A[i][k] * pivot;
            }

        }
    }

    for (int i = 0; i < Rows; i++) {
        U[i] = A[i];
    }
    

    cout << "Matrix L:\n";
    cout << L << endl;
    cout << "Matrix U:\n";
    cout << U << endl;
    

}

void inverseMatrix(vector<vector<double>> triangleMatrix, int Rows, int Cols) {
    
    if (triangleMatrix[0][1] != 0) {
        for (int i = 0; i < Rows; i++) {
            double pivot = triangleMatrix[i][i];
            triangleMatrix[i][i] = 1;
            for (int j = i + 1; j < Cols; j++) {
                triangleMatrix[i][j] /= pivot;
            }
        }

    
    }
};