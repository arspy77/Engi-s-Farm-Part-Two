import cv2
import numpy as np

l_img = cv2.imread('Hijau muda.png', -1)
s_img = cv2.imread('tile_71.png',-1)
x_offset = 0
y_offset = 0

y1, y2 = y_offset, y_offset + s_img.shape[0]
x1, x2 = x_offset, x_offset + s_img.shape[1]

alpha_s = s_img[:, :, 3] / 255.0
alpha_l = 1.0 - alpha_s

for c in range(0, 4):
    l_img[y1:y2, x1:x2, c] = (alpha_s * s_img[:, :, c] +
                              alpha_l * l_img[y1:y2, x1:x2, c])
cv2.imwrite('hm.png',l_img)