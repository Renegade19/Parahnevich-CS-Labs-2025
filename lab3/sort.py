if __name__ == '__main__':
    data = [4, -30, 30, 100, -100, 123, 1, 0, -1, -4]
    result = sorted(data, key=abs, reverse=True)
    print(result)
