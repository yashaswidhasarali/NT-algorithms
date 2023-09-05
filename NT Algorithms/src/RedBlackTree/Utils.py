# Code for left rotate
def left_rotate ( tree , temp ) :
    parent = temp.right
    temp.right = parent.left
    if parent.left != tree.null_node :
        parent.left.parent = temp

    parent.parent = temp.parent
    if temp.parent == None :
        tree.root = parent
    elif temp == temp.parent.left :
        temp.parent.left = parent
    else :
        temp.parent.right = parent
    parent.left = temp
    temp.parent = parent


# Code for right rotate
def right_rotate ( tree , temp ) :
    parent = temp.left
    temp.left = parent.right
    if parent.right != tree.null_node :
        parent.right.parent = temp

    parent.parent = temp.parent
    if temp.parent == None :
        tree.root = parent
    elif temp == temp.parent.right :
        temp.parent.right = parent
    else :
        temp.parent.left = parent
    parent.right = temp
    temp.parent = parent


# Function to print
def print_the_tree (node , indent="" , last = True, root = False ) :
    if node != None and node.value != 0:
        print(indent, end=' ')
        if last :
            if not root:
                print ("R------>",end= ' ')
            indent += "     "
        else :
            print("L------>",end=' ')
            indent += "|    "

        s_color = "Red" if node.color_is_red else "Black"
        print ( str ( node.value ) + " *" + s_color + "*" )
        print_the_tree ( node.left , indent , False, False )
        print_the_tree ( node.right , indent , True, False )


def find_min_inorder(RBTree, node):
    while node.left != RBTree.null_node:
        node = node.left
    return node