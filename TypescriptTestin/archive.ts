// --------------------------------------------------------Query parameter parser
interface parsedURL {
    protocol: string;
    host: string;
    path: string;
    parameters: Map<string,string>;
}

let firstURL: string = "https://cardgame-piotrjagla.vercel.app/Duel/playCard?param=tree&param2=sos";
console.log(firstURL);

const parse = (url: string): parsedURL => {
    let result: parsedURL = {protocol: "", host:"", path:"", parameters: new Map()};

    let protocolSplitter = 0;
    let pathStart= 0;
    let parametersStart = 0;
    let valueStart = 0;
    for(let i = 0 ; i < url.length ; i++) {
        if(url.charAt(i) === ':') {
            result.protocol = url.substring(0, i);
            protocolSplitter = i+3;
            i+=2;
        }
        else if(url.charAt(i) === '/') {
            if(result.host.length !== 0) {
                result.path= url.substring(pathStart, i);
            }
            else {
                pathStart = i+1;
                result.host = url.substring(protocolSplitter, i);
            }
        }
        else if(url.charAt(i) === '?') {
            result.path= url.substring(pathStart, i);
            result.parameters = new Map();
            parametersStart = i + 1;
        }
        else if(url.charAt(i) === '='){
            valueStart = i + 1;
        }
        else if(url.charAt(i) === '&') {
            result.parameters.set(url.substring(parametersStart, valueStart - 1), url.substring(valueStart, i));
            parametersStart = i + 1;
        }
        else if(i + 1 === url.length) {
            result.parameters.set(url.substring(parametersStart, valueStart - 1), url.substring(valueStart, i+1));
        }

    }

    return result;
}

let r:parsedURL = parse(firstURL);
console.log("protocol: " + r.protocol);
console.log("host: " + r.host);
console.log("path: " + r.path);
console.log("parameters: ");
console.log(r.parameters);

// ----------------------------------------------------- Binary tree invert
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


// ---------------------------------------Quicksort



const quickSort = (arr: number[], start: number, end: number) => {
    if(start >= end) {
        return;
    }

    let pivot:number = arr[end];

    let pIndex = start;
    for(let i = start; i < end ; ++i) {
        if(arr[i] < pivot) {
            let temp = arr[i];
            arr[i] = arr[pIndex];
            arr[pIndex] = temp;
            ++pIndex;
        }
    }
    let temp = arr[pIndex];
    arr[pIndex] = arr[end];
    arr[end] = temp;


    quickSort(arr, start, pIndex - 1);
    quickSort(arr, pIndex + 1, end);

}


const sort = (arr: number[]) => {
    quickSort(arr, 0, arr.length - 1);

}

let arr = [9,8,6,4,2,1,7,5,3];
sort(arr);
console.log(arr);
