def field(items, *args):
    assert len(args) > 0, "Необходимо передать хотя бы одно имя поля"
    for item in items:
        if len(args) == 1:
            key = args[0]
            if key in item and item[key] is not None:
                yield item[key]
        else:
            d = {}
            for key in args:
                if key in item and item[key] is not None:
                    d[key] = item[key]
            if d:
                yield d


if __name__ == '__main__':
    goods = [
        {'title': 'Ковер', 'price': 2000, 'color': 'green'},
        {'title': 'Диван для отдыха', 'color': 'black'}
    ]
    print(list(field(goods, 'title')))
    print(list(field(goods, 'title', 'price')))
