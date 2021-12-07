#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

class Matrix {
private:
    int rows;
    int colums;
    vector<vector<double>> matrix;
public:
    Matrix(int row, int colum) : rows(row), colums(colum), matrix(row, vector<double>(colum)) {}
    void entreMatrix();
    Matrix(int row, int colum, vector<vector<double>> A) : rows(row), colums(colum), matrix(A) {}
    void print();
    void identity_matrix(Matrix A);
    void swap_row(int place_row);
    void GausseJordan(Matrix A);
    void inverseByLU();
    //

    double& operator() (int row, int col);
    Matrix& operator=(const Matrix& other);
    friend Matrix operator+(const Matrix& lmat, const Matrix& rmat);
    friend Matrix operator-(const Matrix& lmat, const Matrix& rmat);
    friend Matrix operator*(const Matrix& lmat, const Matrix& rmat);
    Matrix Get_part(int i);
    Matrix Insert_part(Matrix a, int part);

};


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
    /*vector<vector<double>> A{ {2, 4, 3, 5}, {-4, -7, -5, -8}, {6, 8, 2, 9}, {4, 9, -2, 14} };
    Matrix C(4, 4, A);
    C.print();*/
    return 0;
}
void Matrix::inverseByLU() {

    Matrix L(rows, colums);
    Matrix U(rows, colums);
    Matrix C(rows, colums);
    C.matrix = matrix;


    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < colums; j++) {
            if (i == j) {
                L.matrix[i][i] = 1;
                U.matrix[i][j] = 0;
            }
            else {
                L.matrix[i][j] = 0;
                U.matrix[i][j] = 0;
            }
        }
    }


    for (int i = 0; i < rows; i++) {

        for (int j = i + 1; j < colums; j++) {
            double pivot = C.matrix[j][i] / C.matrix[i][i];
            L.matrix[j][i] = pivot;
            for (int k = 0; k < colums; k++) {
                C.matrix[j][k] -= C.matrix[i][k] * pivot;
            }

        }
    }

    for (int i = 0; i < rows; i++) {
        U.matrix[i] = C.matrix[i];
    }


    cout << "Matrix L:\n";
    L.print();
    cout << "Matrix U:\n";
    U.print();
    L.GausseJordan(L);
    U.GausseJordan(U);
    Matrix res(rows, colums);
    cout << "invers LU: " << endl;
    res = U * L;
    res.print();
}


void Matrix::identity_matrix(Matrix A) {
    if (A.matrix[0][0] == 0) {
        A.swap_row(0);
    }
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < A.colums; j++) {
            matrix[i][j] = A.matrix[i][j];
            matrix[i][i + A.colums] = 1;
        }
    }
}
void Matrix::entreMatrix() {
    double element = 0;
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < colums; j++) {
            cout << "a[" << i << "][" << j << "] ";
            cin >> element;
            matrix[i][j] = element;
        }
    }
}
void Matrix::print() {
    for (int i = 0; i < rows; i++) {
        cout << "( ";
        for (int j = 0; j < colums; j++) {
            cout << matrix[i][j] << " ";
        }
        cout << ")" << endl;
    }
}

void Matrix::swap_row(int place_row) {
    vector<double> temp = matrix[place_row];
    for (int i = 0; i < rows; i++) {
        if (matrix[i][0] != 0) {
            matrix[place_row] = matrix[i];
            matrix[i] = temp;
            break;
        }
    }
}


void Matrix::GausseJordan(Matrix A) {
    Matrix B(A.rows, A.colums * 2); // створюємо розширену матрицю
    B.identity_matrix(A);
    //A - задана матриця
    //B - розширена матриця

    //нулі під головною діагоналлю (починаємо з лівого вергньохо рядочка)

    for (int i = 0; i < A.rows; i++) {
        for (int j = 0; j < B.colums; j++) {
            B.matrix[i][j] = B.matrix[i][j] / A.matrix[i][i]; //робимо одиниці на діагоналі
        }
        for (int i_nex = i + 1; i_nex < A.rows; i_nex++) {
            double k = B.matrix[i_nex][i] / B.matrix[i][i]; //коефіцєнт что б зрубити нулі
            for (int j = 0; j < B.colums; j++) {
                B.matrix[i_nex][j] = B.matrix[i_nex][j] - B.matrix[i][j] * k; //віднімання попереднього рядочка помноженого на коефіцієнт
            }
        }
        //оновлення матрциці А
        for (int k = 0; k < A.rows; k++) {
            for (int j = 0; j < A.colums; j++) {
                A.matrix[k][j] = B.matrix[k][j];
            }
        }
    }
    //нулі над головною діагоналлю (починаємо з правого ніжнього рядочка)
    for (int i = A.rows - 1; i > -1; i--) {
        for (int j = B.colums - 1; j > -1; j--) {
            B.matrix[i][j] = B.matrix[i][j] / A.matrix[i][i];  //одиниці на діагоналі
        }
        for (int i_nex = i - 1; i_nex > -1; i_nex--) {
            double k = B.matrix[i_nex][i] / B.matrix[i][i]; //коефіцєнт что б зрубити нулі
            for (int j = B.colums; j > -1; j--) {
                B.matrix[i_nex][j] = B.matrix[i_nex][j] - B.matrix[i][j] * k;//віднімання попереднього рядочку(знизу) помноженого на коефіцієнт

            }
        }

    }
    //заповнення матрицю А результатом

    for (int i = 0; i < A.rows; i++) {
        for (int j = 0; j < A.colums; j++) {
            matrix[i][j] = B.matrix[i][j + colums];
        }
    }

}


///////////////////////////////////

double& Matrix::operator()(int row, int col)
{
    if (row < this->rows || col < this->colums) {
        return this->matrix[row][col];
    }
    else {
        throw invalid_argument("Entered values are greater than Matrix dimension");
    }
}

Matrix& Matrix::operator=(const Matrix& other)
{
    if (this == &other) {
        return *this;
    }
    if (other.rows <= this->rows && other.colums <= this->colums) {
        for (int i = 0; i < other.rows; i++) {
            for (int j = 0; j < other.rows; j++) {
                this->matrix[i][j] = other.matrix[i][j];
            }
        }
        return *this;
    }
    else {
        throw invalid_argument("Dimension of the Matrix you try to assign is greater than the Matrix on the left");
    }
}

Matrix operator+(const Matrix& lmat, const Matrix& rmat)
{
    Matrix res(lmat.rows, lmat.colums);
    if (lmat.rows == rmat.rows && lmat.colums == rmat.colums) {
        for (int i = 0; i < lmat.rows; i++) {
            for (int j = 0; j < lmat.rows; j++) {
                res.matrix[i][j] = lmat.matrix[i][j] + rmat.matrix[i][j];
            }
        }
        return res;
    }
    else {
        res = lmat;
    }
    return res;
}

Matrix operator-(const Matrix& lmat, const Matrix& rmat)
{
    Matrix res(lmat.rows, lmat.colums);
    if (lmat.rows == rmat.rows && lmat.colums == rmat.colums) {
        for (int i = 0; i < lmat.rows; i++) {
            for (int j = 0; j < lmat.rows; j++) {
                res.matrix[i][j] = lmat.matrix[i][j] - rmat.matrix[i][j];
            }
        }
        return res;
    }
    else {
        res = lmat;
    }
    return res;

}

Matrix operator*(const Matrix& lmat, const Matrix& rmat)
{
    int mutual_dim = lmat.rows;
    if (lmat.rows > 1 && lmat.colums > 1) {
        if (lmat.rows == rmat.rows && lmat.colums == rmat.colums) {
            while (std::log2(mutual_dim) - int(log2(mutual_dim)) != 0) {
                mutual_dim++;
            }

            Matrix mat1(mutual_dim, mutual_dim);
            Matrix mat2(mutual_dim, mutual_dim);
            Matrix res_mat(mutual_dim, mutual_dim);

            mat1 = lmat;
            mat2 = rmat;

            Matrix a11 = mat1.Get_part(1);
            Matrix a12 = mat1.Get_part(2);
            Matrix a21 = mat1.Get_part(3);
            Matrix a22 = mat1.Get_part(4);

            Matrix b11 = mat2.Get_part(1);
            Matrix b12 = mat2.Get_part(2);
            Matrix b21 = mat2.Get_part(3);
            Matrix b22 = mat2.Get_part(4);

            Matrix s1 = b12 - b22;
            Matrix s2 = a11 + a12;
            Matrix s3 = a21 + a22;
            Matrix s4 = b21 - b11;
            Matrix s5 = a11 + a22;
            Matrix s6 = b11 + b22;
            Matrix s7 = a12 - a22;
            Matrix s8 = b21 + b22;
            Matrix s9 = a11 - a21;
            Matrix s10 = b11 + b12;

            Matrix p1 = a11 * s1;
            Matrix p2 = s2 * b22;
            Matrix p3 = s3 * b11;
            Matrix p4 = a22 * s4;
            Matrix p5 = s5 * s6;
            Matrix p6 = s7 * s8;
            Matrix p7 = s9 * s10;

            res_mat.Insert_part(p5 + p4 - p2 + p6, 1);
            res_mat.Insert_part(p1 + p2, 2);
            res_mat.Insert_part(p3 + p4, 3);
            res_mat.Insert_part(p5 + p1 - p3 - p7, 4);

            if (lmat.rows < mutual_dim) {
                Matrix buff(lmat.rows, lmat.colums);
                for (int i = 0; i < lmat.rows; i++) {
                    for (int j = 0; j < lmat.rows; j++) {
                        buff.matrix[i][j] = res_mat.matrix[i][j];
                    }
                }
                res_mat.matrix = buff.matrix;
                res_mat.rows = lmat.rows;
                res_mat.colums = lmat.colums;
            }
            return res_mat;
        }
    }
    double f = lmat.matrix[0][0];
    double s = rmat.matrix[0][0];
    double k = f * s;
    Matrix res(lmat.rows, lmat.colums);
    res.matrix[0][0] = k;
    return res;
}

Matrix Matrix::Get_part(int a)
{
    if (rows >= 2 && colums >= 2) {
        Matrix res_mat(rows / 2, colums / 2);
        switch (a)
        {
        case 1:
            for (int i = 0; i < rows / 2; i++) {
                for (int j = 0; j < rows / 2; j++) {
                    res_mat.matrix[i][j] = this->matrix[i][j];
                }
            }
            return res_mat;
            break;
        case 2:
            for (int i = 0; i < rows / 2; i++) {
                for (int j = rows / 2; j < rows; j++) {
                    res_mat.matrix[i][j - rows / 2] = this->matrix[i][j];
                }
            }
            return res_mat;
            break;
        case 3:
            for (int i = rows / 2; i < rows; i++) {
                for (int j = 0; j < rows / 2; j++) {
                    res_mat.matrix[i - rows / 2][j] = this->matrix[i][j];
                }
            }
            return res_mat;
            break;
        case 4:
            for (int i = rows / 2; i < rows; i++) {
                for (int j = rows / 2; j < rows; j++) {
                    res_mat.matrix[i - rows / 2][j - rows / 2] = this->matrix[i][j];
                }
            }
            return res_mat;
            break;
        default:
            break;
        }
    }
    else {
        Matrix res_mat(1, 1);
        switch (a) {
        case 1:
            res_mat(0, 0) = this->matrix[0][0];
            break;
        case 2:
            res_mat(0, 1) = this->matrix[0][1];
            break;
        case 3:
            res_mat(1, 0) = this->matrix[1][0];
            break;
        case 4:
            res_mat(1, 1) = this->matrix[1][1];
            break;
        }
        return res_mat;
    }
    return *this;
}

Matrix Matrix::Insert_part(Matrix a, int part)
{
    switch (part) {
    case 1:
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.rows; j++) {
                this->matrix[i][j] = a.matrix[i][j];
            }
        }
        break;
    case 2:
        for (int i = 0; i < a.rows; i++) {
            for (int j = a.rows; j < a.rows * 2; j++) {
                this->matrix[i][j] = a.matrix[i][j - a.rows];
            }
        }
        break;
    case 3:
        for (int i = a.rows; i < a.rows * 2; i++) {
            for (int j = 0; j < a.rows; j++) {
                this->matrix[i][j] = a.matrix[i - a.rows][j];
            }
        }
        break;
    case 4:
        for (int i = a.rows; i < a.rows * 2; i++) {
            for (int j = a.rows; j < a.rows * 2; j++) {
                this->matrix[i][j] = a.matrix[i - a.rows][j - a.rows];
            }
        }
        break;
    }
    return *this;
}
