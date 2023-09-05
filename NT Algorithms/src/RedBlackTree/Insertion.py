import TreeNode
import Utils

# Insert Node
def insert_the_node(RBTree, key):
    node = TreeNode.TreeNode(key)
    node.parent = None
    node.value = key
    node.left = RBTree.null_node
    node.right = RBTree.null_node
     # Set temp null node colour as Red
    node.color_is_red = True

    parent = None
    temp = RBTree.root

    # Traverse and check where the NEW NODE can be placed - Binary search tree technique
    while temp != RBTree.null_node :
        parent = temp
        if node.value >= temp.value : # 
            temp = temp.right
        else : # 
            temp = temp.left
    # Assign parent to the node
    node.parent = parent

    #* Case 1 Where tree is empty *#
    if parent == None :
        RBTree.root = node
        node.color_is_red = False
        return

    #* Case 2 If tree is not empty add leaf with the color red (LEFT OR RIGHT)*#
    elif node.value < parent.value : # If node value lesser than parent value, attach it to the left
        parent.left = node
    else :
        parent.right = node # Else attach it to the right 

    # Parent's parent is root, already attached, so exit
    if node.parent.parent == None :
        return

    fixing_node = node # assign the fix needed node to fixing_node

    #* Case 3 when parent node's color is red, rotate and balance the tree until it is colored to black*#
    while fixing_node.parent.color_is_red:

        #* LEFT *#
        if fixing_node.parent == fixing_node.parent.parent.right:
            temp_node = fixing_node.parent.parent.left
            if temp_node.color_is_red:
                temp_node.color_is_red = False
                fixing_node.parent.color_is_red = False
                fixing_node.parent.parent.color_is_red = True
                fixing_node = fixing_node.parent.parent
            else:
                if fixing_node == fixing_node.parent.left:
                    fixing_node = fixing_node.parent
                    Utils.right_rotate(RBTree, fixing_node)
                fixing_node.parent.color_is_red = False
                fixing_node.parent.parent.color_is_red = True
                Utils.left_rotate(RBTree, fixing_node.parent.parent)

        #* RIGHT *#
        else:
            temp_node = fixing_node.parent.parent.right
            if temp_node.color_is_red:
                temp_node.color_is_red = False
                fixing_node.parent.color_is_red = False
                fixing_node.parent.parent.color_is_red = True
                fixing_node = fixing_node.parent.parent
            else:
                if fixing_node == fixing_node.parent.right:
                    fixing_node = fixing_node.parent
                    Utils.left_rotate(RBTree, fixing_node)
                fixing_node.parent.color_is_red = False
                fixing_node.parent.parent.color_is_red = True
                Utils.right_rotate(RBTree, fixing_node.parent.parent)
        if fixing_node == RBTree.root: # If it reaches the root then break the loop
            break
    RBTree.root.color_is_red = False
    return