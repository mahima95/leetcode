from numpy import asarray
import numpy as np


ENERGY_LEVEL = [100, 113, 110, 85, 105, 102, 86, 63, 81,
                101, 94, 106, 101, 79, 94, 90, 97]


# The brute force method to solve first problem


def find_significant_energy_increase_brute(A):

    # array for change to calculate max subarray for differences

    change = []

    if len(A) == 0:
        return None

    else:
        for x in range(1, len(A)):
            change.append(A[x] - A[x - 1])
        max_sum = 0
        start = 0
        end = 1
        for i in range(0, len(change)):
            sum = 0
            for j in range(i, len(change)):
                sum += change[j]
                if sum > max_sum:
                    max_sum = sum
                    start = i
                    end = j+1
        return start, end


def find_significant_energy_increase_recursive(A):

    # array for change to calculate max subarray for differences

    change = []
    if len(A) == 0:
        return None
    else:
        for x in range(1, len(A)):
            change.append(A[x]-A[x-1])
        return tuple(recursive_helper(change, 0, len(change)-1)[0])


def recursive_helper(change, low=0, high=-1):

    if low > high:
        return [-1, -1, 0]

    if low == high:
        return [[low, high+1], change[low]]

    mid = (low + high)//2

    left = recursive_helper(change, low, mid)

    right = recursive_helper(change, mid+1, high)

    crossing = find_maximum_crossing_subarray(change, low, mid, high)

    if left[1] >= right[1] and left[1] >= crossing[1]:
        return left

    elif right[1] >= left[1] and right[1] >= crossing[1]:
        return right

    else:
        return crossing


def find_maximum_crossing_subarray(A, low, mid, high):

    ls = float("-inf")

    leftsum = 0

    l_index = r_index = 0

    for i in range(mid, low - 1, -1):
        leftsum = leftsum + A[i]

        if leftsum > ls and not (A[i] < 0 and leftsum == 0):
            ls = leftsum
            l_index = i
            rs = float("-inf")

        rsum = 0
        for j in range(mid + 1, high + 1):
            rsum = rsum + A[j]
            if rsum > rs and not (A[i] < 0 and rsum == 0):
                rs = rsum
                r_index = j+1

    if ls > float("-inf") and rs > float("-inf"):
        return [[l_index, r_index], ls + rs]


def find_significant_energy_increase_iterative(A):

    """Return a tuple (i,j) where A[i:j] is the most
    significant energy increase period.
    time complexity = O(n)"""

    change = []
    if len(A) == 0:
        return None
    else:
        for x in range(1, len(A)):
            change.append(A[x]-A[x-1])
        left = right = templeft = tempsum = 0
        max_sum = float('-inf')
        for i in range(len(change)):
            tempsum += change[i]
            if tempsum > max_sum:
                max_sum = tempsum
                left = templeft
                right = i+1
            if tempsum < 0:
                tempsum = 0
                templeft = i+1
        return left, right


# The Strassen Algorithm to do the matrix multiplication


def square_matrix_multiply_strassens(A, B):

    """
    Return the product AB of matrix multiplication.
    Assume len(A) is a power of 2
    """

    A = asarray(A)

    B = asarray(B)

    assert A.shape == B.shape

    assert A.shape == A.T.shape

    assert (len(A) & (len(A) - 1)) == 0, "A is not a power of 2"

    n = len(A)

    C = [[0 for j in range(n)] for i in range(n)]

    if n == 1:

        for i in range(n):

            for k in range(n):

                for j in range(n):

                    C[i][j] += A[i][k] * B[k][j]

        return C

    else:

        x = n // 2

        change1 = [[0 for i in range(0, x)] for j in range(0, x)]
        change2 = [[0 for i in range(0, x)] for j in range(0, x)]
        A21 = [[0 for i in range(0, x)] for j in range(0, x)]
        A22 = [[0 for i in range(0, x)] for j in range(0, x)]
        B11 = [[0 for i in range(0, x)] for j in range(0, x)]
        B12 = [[0 for i in range(0, x)] for j in range(0, x)]
        B21 = [[0 for i in range(0, x)] for j in range(0, x)]
        B22 = [[0 for i in range(0, x)] for j in range(0, x)]

        temp_a = [[0 for i in range(0, x)] for j in range(0, x)]
        temp_b = [[0 for i in range(0, x)] for j in range(0, x)]

        for i in range(0, x):

            for j in range(0, x):
                change1[i][j] = A[i][j]
                change2[i][j] = A[i][j + x]
                A21[i][j] = A[i + x][j]
                A22[i][j] = A[i + x][j + x]

                B11[i][j] = B[i][j]
                B12[i][j] = B[i][j + x]
                B21[i][j] = B[i + x][j]
                B22[i][j] = B[i + x][j + x]
    # P1 to P7 calculations:

        temp_b = np.subtract(B12, B22)  # S1
        p1 = square_matrix_multiply_strassens(change1, temp_b)

        temp_a = np.add(change1, change2)  # S2
        p2 = square_matrix_multiply_strassens(temp_a, B22)

        temp_a = np.add(A21, A22)
        p3 = square_matrix_multiply_strassens(temp_a, B11)

        temp_b = np.subtract(B21, B11)
        p4 = square_matrix_multiply_strassens(A22, temp_b)

        temp_a = np.add(change1, A22)
        temp_b = np.add(B11, B22)
        p5 = square_matrix_multiply_strassens(temp_a, temp_b)

        temp_a = np.subtract(change2, A22)
        temp_b = np.add(B21, B22)
        p6 = square_matrix_multiply_strassens(temp_a, temp_b)

        temp_a = np.subtract(change1, A21)
        # S9
        temp_b = np.add(B11, B12)
        p7 = square_matrix_multiply_strassens(temp_a, temp_b)

        C12 = np.add(p1, p2)
        C21 = np.add(p3, p4)

        temp_a = np.add(p5, p4)
        temp_b = np.add(temp_a, p6)
        C11 = np.subtract(temp_b, p2)

        temp_a = np.add(p5, p1)

        temp_b = np.add(p3, p7)

        C22 = np.subtract(temp_a, temp_b)

    # C = [[0 for i in range(0, n)] for j in range(0, n)]

        for i in range(0, x):

            for j in range(0, x):
                C[i][j] = C11[i][j]
                C[i][j + x] = C12[i][j]
                C[i + x][j] = C21[i][j]
                C[i + x][j + x] = C22[i][j]
        return C
    pass


def power_of_matrix_navie(A, k):

    if k < 0:
        return None
    elif k == 0:
        return np.identity(len(A))
    elif k == 1:
        return A
    else:
        product = np.identity(len(A))
        # runs k times to generate A to the power of k
        for i in range(k):
            product = square_matrix_multiply_strassens(A, product)

        return product


# Calculate the power of a matrix in O(log k)
def power_of_matrix_divide_and_conquer(A, k):

    if k < 0:
        return None
    elif k == 0:
        return np.identity(len(A))
    elif k == 1:
        return A
    else:
        S = power_of_matrix_divide_and_conquer(A, int(k/2))
        if k % 2 == 0:
            return square_matrix_multiply_strassens(S, S)
        else:
            return square_matrix_multiply_strassens(S,
                                                    square_matrix_multiply_strassens
                                                    (S, A))


# test cases


print("Matrix Multiplication strassen : ",
      square_matrix_multiply_strassens([[1, 2], [3, 4]],
                                       [[5, 6], [7, 8]]))
print("power:", power_of_matrix_navie([[1, 2], [3, 4]],  6))
print("power:", power_of_matrix_divide_and_conquer([[1, 2], [3, 4]],  6))


# Test Cases for brute force

print(find_significant_energy_increase_brute([110, 109, 107, 104, 100]))
print(find_significant_energy_increase_brute(ENERGY_LEVEL))
print(find_significant_energy_increase_brute([1, 2, -3, -84, -905, 6, 7, 8]))
print(find_significant_energy_increase_brute([]))
print(find_significant_energy_increase_brute([4, 5, 3, 2, 1]))
print(find_significant_energy_increase_brute([-4, -5, -3, -2]))


# Test cases


print(find_significant_energy_increase_recursive([110, 109, 107, 104, 100]))
print(find_significant_energy_increase_recursive(ENERGY_LEVEL))
print(find_significant_energy_increase_recursive([1, 2, -3,
                                                 -84, -905, 6, 7, 8]))
print(find_significant_energy_increase_recursive([]))
print(find_significant_energy_increase_recursive([4, 5, 3, 2, 1]))
print(find_significant_energy_increase_recursive([-4, -5, -3, -2]))


# Test cases


print(find_significant_energy_increase_iterative([110, 109, 107, 104, 100]))
print(find_significant_energy_increase_iterative(ENERGY_LEVEL))
print(find_significant_energy_increase_iterative([1, 2, -3,
                                                 -84, -905, 6, 7, 8]))
print(find_significant_energy_increase_iterative([]))
print(find_significant_energy_increase_iterative([4, 5, 3, 2, 1]))
print(find_significant_energy_increase_iterative([-4, -5, -3, -2]))
