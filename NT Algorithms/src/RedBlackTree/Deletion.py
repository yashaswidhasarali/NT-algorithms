import Utils

# Function to swap nodes
def swap ( RBTree , first , second ) :
    if first.parent == None :
        RBTree.root = second
    elif first == first.parent.left :
        first.parent.left = second
    else :
        first.parent.right = second
    second.parent = first.parent

def delete_the_node ( RBTree , node, key ):
    node_to_be_deleted = RBTree.null_node

    # Search for the node to be deleted by Binary search technique
    while node != RBTree.null_node :
        if node.value == key :
            node_to_be_deleted = node

        if key >= node.value:
            node = node.right # Traverse right
        else :
            node = node.left # Traverse left

    min_node = node_to_be_deleted # Have a new variable for node to be deleted to keep the old one for future use
    org_color_of_node_to_delete = min_node.color_is_red # storing the original color of node to be deleted


    #* CASE 1 Where only one child *#
    # If RIGHT node is null, just swap the node to be deleted with the LEFT node
    if (node_to_be_deleted.right == RBTree.null_node) :
        temp_node = node_to_be_deleted.left
        swap ( RBTree, node_to_be_deleted , node_to_be_deleted.left )

    # If RIGHT node is null, just swap the node to be deleted with the LEFT node
    elif node_to_be_deleted.left == RBTree.null_node :
        temp_node = node_to_be_deleted.right
        swap (RBTree, node_to_be_deleted , node_to_be_deleted.right )

    #* CASE 2 Where there is two children but one of them is red find inorder successor, swap*#
    # This else will be entered if there'sibling_of_temp_node both children to the node to be deleted
    else :
        # Finding the inorder successor for the node to be deleted - function find_min_inorder does that
        min_node = Utils.find_min_inorder (RBTree, node_to_be_deleted.right ) # Now make use of min_node for storing inorder successor
        org_color_of_node_to_delete = min_node.color_is_red # storing the color of inorder successor
        temp_node = min_node.right

        # Incase node to be deleted is the parent of min node found, swap those two
        if min_node.parent == node_to_be_deleted :
            temp_node.parent = min_node
        else :
            # Moving the node to be deleted to the leaf so it can be removed
            swap (RBTree, min_node , min_node.right )
            min_node.right = node_to_be_deleted.right
            min_node.right.parent = min_node

        swap (RBTree, node_to_be_deleted , min_node )
        min_node.left = node_to_be_deleted.left
        min_node.left.parent = min_node
        min_node.color_is_red = node_to_be_deleted.color_is_red

    #* CASE 3 Where node to be deleted is black, double black situation should be solved *#
    # If the color of node is black FIXING of RBTree is needed is NEEDED to balance
    if org_color_of_node_to_delete == False :

        # While should go on until it reaches the ROOT and it is black
        while temp_node != RBTree.root and not temp_node.color_is_red :

            #*RIGHT*#
            if temp_node == temp_node.parent.right:
                sibling_of_temp_node = temp_node.parent.left

                #* Case 3.1 If sibling is red, need to do left rotation *#
                if sibling_of_temp_node.color_is_red :
                    sibling_of_temp_node.color_is_red = False
                    temp_node.parent.color_is_red = True
                    Utils.right_rotate (RBTree, temp_node.parent )
                    sibling_of_temp_node = temp_node.parent.left

                #* Case 3.2 sibling is black and both of its children are black should perform recolouring*#
                if not sibling_of_temp_node.right.color_is_red and not sibling_of_temp_node.right.color_is_red :
                    sibling_of_temp_node.color_is_red = True
                    temp_node = temp_node.parent

                #* Case 3.3 Sibling is black but one of the children is red perform rotation and rebalance *#
                else :
                    if not sibling_of_temp_node.left.color_is_red :
                        sibling_of_temp_node.color_is_red = True
                        sibling_of_temp_node.right.color_is_red = False
                        Utils.left_rotate ( sibling_of_temp_node )
                        sibling_of_temp_node = temp_node.parent.left

                    sibling_of_temp_node.color_is_red = temp_node.parent.color_is_red
                    temp_node.parent.color_is_red = False
                    sibling_of_temp_node.left.color_is_red = False
                    Utils.right_rotate ( temp_node.parent )
                    temp_node = RBTree.root

            #* LEFT*#
            else:
                sibling_of_temp_node = temp_node.parent.right

                #* Case 3.1 If sibling is red, need to do left rotation *#
                if sibling_of_temp_node.color_is_red :
                    sibling_of_temp_node.color_is_red = False
                    temp_node.parent.color_is_red = True
                    Utils.left_rotate (RBTree, temp_node.parent )
                    sibling_of_temp_node = temp_node.parent.right
                
                #* Case 3.2 sibling is black and both of its children are black should perform recolouring*#
                if not sibling_of_temp_node.left.color_is_red and not sibling_of_temp_node.right.color_is_red :
                    sibling_of_temp_node.color_is_red = True
                    temp_node = temp_node.parent
                
                #* Case 3.3 Sibling is black but one of the children is red perform rotation and rebalance *#
                else :
                    if not sibling_of_temp_node.right.color_is_red :
                        sibling_of_temp_node.left.color_is_red = False
                        sibling_of_temp_node.color_is_red = True
                        Utils.right_rotate (RBTree, sibling_of_temp_node )
                        sibling_of_temp_node = temp_node.parent.right

                    sibling_of_temp_node.color_is_red = temp_node.parent.color_is_red
                    temp_node.parent.color_is_red = False
                    sibling_of_temp_node.right.color_is_red = False
                    Utils.left_rotate (RBTree, temp_node.parent )
                    temp_node = RBTree.root

        temp_node.color_is_red = False
