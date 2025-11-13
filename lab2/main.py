from color import Color
from rectangle import Rectangle
from square import Square
from circle import Circle

def main():
    red = Color("Красный")
    blue = Color("Синий")

    rect = Rectangle(4, 6, red)
    sq = Square(5, blue)
    circle = Circle(3, red)

    figures = [rect, sq, circle]

    for fig in figures:
        print(fig)
        print(f"Площадь: {fig.area()}")
        print(f"Периметр: {fig.perimeter()}")
        print("-" * 30)

if __name__ == "__main__":
    main()
