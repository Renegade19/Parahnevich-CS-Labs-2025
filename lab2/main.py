from color import Color
from rectangle import Rectangle
from square import Square
from circle import Circle

def main():
    red = Color("Red")
    blue = Color("Blue")
    green = Color("Green")

    rect = Rectangle(4, 5, red)
    sq = Square(3, blue)
    circ = Circle(2.5, green)

    figures = [rect, sq, circ]
    for f in figures:
        print(f)

if __name__ == "__main__":
    main()
