# -*- coding: utf-8 -*-

def convert(str_input, space_character, first_upper = False):  # one_string:输入的字符串；space_character:字符串的间隔符，以其做为分隔标志

    string_list = str(str_input).split(space_character)  # 将字符串转化为list
    first = string_list[0].lower()
    if first_upper:
        first = '%s%s' % (first[0].upper(), first[1:])
    others = string_list[1:]
    others_capital = [word.capitalize() for word in others]  # str.capitalize():将字符串的首字母转化为大写
    others_capital[0:0] = [first]
    hump_string = ''.join(others_capital)  # 将list组合成为字符串，中间无连接符。
    return hump_string


if __name__ == '__main__':
    print(convert('asd', '_', True))