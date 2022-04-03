import tensorflow as tf
import cv2
import numpy as np

def utkarsh(dir):
    new_model = tf.keras.models.load_model('Version4.h5')
    image = cv2.imread(dir)
    original = image.copy()

    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    blur = cv2.GaussianBlur(gray, (3, 3), 0)
    canny = cv2.Canny(blur, 120, 255, 1)

    cnts = cv2.findContours(canny, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
    cnts = cnts[0] if len(cnts) == 2 else cnts[1]
    min_area = 100
    images = []
    boundary = []
    classes = []
    for c in cnts:
        area = cv2.contourArea(c)
        if area > min_area:
            x, y, w, h = cv2.boundingRect(c)
            arr = [x, y, w, h]
            boundary.append(arr)

    boundary = np.array(sorted(boundary, key=lambda x: x[0]))
    for i in boundary:
        x, y, w, h = i
        cv2.rectangle(image, (x, y), (x + w, y + h), (36, 255, 12), 2)
        char = original[y:y + h, x:x + w]
        images.append(char)

    for i in images:
        i = cv2.cvtColor(i, cv2.COLOR_BGR2GRAY)
        i = cv2.resize(i, (20, 20))

        x = np.asarray(i)
        add_c = np.zeros((20, 4))
        add_r = np.zeros((4, 28))
        x = np.concatenate((add_c, x), axis=1)
        x = np.concatenate((x, add_c), axis=1)
        x = np.concatenate((x, add_r), axis=0)
        x = np.concatenate((add_r, x), axis=0)

        x = x.reshape((1, 28, 28, 1))
        x = x / 255
        classes.append(np.argmax(new_model.predict(x)))

    dict = {1: 'A', 2: 'B', 3: 'C', 4: 'D', 5: 'E', 6: 'F', 7: 'G', 8: 'H', 9: 'I', 10: 'J', 11: 'K',
            12: 'L', 13: 'M', 14: 'N', 15: 'O', 16: 'P', 17: 'Q', 18: 'R', 19: 'S', 20: 'T', 21: 'U',
            22: 'V', 23: 'W', 24: 'X', 25: 'Y', 26: 'Z', 27: 'a', 28: 'b', 29: 'd', 30: 'e', 31: 'f',
            32: 'g', 33: 'h', 34: 'n', 35: 'q', 36: 'r', 37: 't'}

    res = ''
    for c in classes:
        res += str(dict[c + 1])

    return res

def predict(Theta1, Theta2, X):
    m = X.shape[0]
    one_matrix = np.ones((m, 1))
    X = np.append(one_matrix, X, axis=1)  # Adding bias unit to first layer
    z2 = np.dot(X, Theta1.transpose())
    a2 = 1 / (1 + np.exp(-z2))  # Activation for second layer
    one_matrix = np.ones((m, 1))
    a2 = np.append(one_matrix, a2, axis=1)  # Adding bias unit to hidden layer
    z3 = np.dot(a2, Theta2.transpose())
    a3 = 1 / (1 + np.exp(-z3))  # Activation for third layer
    p = (np.argmax(a3, axis=1))  # Predicting the class on the basis of max value of hypothesis
    return p

def utkarsh1():
    import numpy as np
    from PIL import Image

    img = Image.open('img1.jpg')
    img = img.resize((28, 28))

    # Converting rgb to grayscale image
    img = img.convert('L')

    # Extracting pixel matrix of image and converting it to a vector of (1, 784)
    x = np.asarray(img)
    vec = np.zeros((1, 784))
    k = 0
    for i in range(28):
        for j in range(28):
            vec[0][k] = x[i][j]
            k += 1

    # Loading Thetas
    Theta1 = np.loadtxt('theta1.txt')
    Theta2 = np.loadtxt('theta2.txt')

    # Calling function for prediction
    pred = predict(Theta1, Theta2, vec / 255)
    return str(pred[0])



