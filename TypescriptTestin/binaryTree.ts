class MyNode{
    right: MyNode;
    left: MyNode;
    value: number;

    constructor(left: MyNode, right: MyNode, value: number) {
        this.right = right;
        this.left = left;
        this.value = value
    }
}


const traverse = (root: MyNode): void => {
    if(root === null) {
        return;
    }

    traverse(root.left);
    console.log(root.value);
    traverse(root.right);
}

const invert = (root: MyNode): void => {
    if(root === null) {
        return;
    }

    let temp: MyNode = root.left;
    root.left = root.right;
    root.right = temp;

    invert(root.left);
    invert(root.right);

}


let groot: MyNode = new MyNode(
    new MyNode(null,null,1),
    new MyNode(new MyNode(null,null,3),new MyNode(null,null,5),4),
    2
);

console.log("traversing before invert");
traverse(groot);
console.log("after invert");
invert(groot);
traverse(groot);


