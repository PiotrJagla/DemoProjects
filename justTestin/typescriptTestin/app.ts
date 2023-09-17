function mergeSort(arr: number[]){
    let l:number = arr.length;
    if(l === 1) {
        return;
    }
    let middle:number = l/2;

    let left: number[] = arr.slice(0, middle); 
    let right: number[] = arr.slice(middle, l);
    mergeSort(left);
    mergeSort(right);


    let k = 0;
    let i = 0;
    let j = 0;
    while(i < left.length && j < right.length){ 
        if(left[i] <= right[j]) {
            arr[k] = left[i];
            i++;
        }
        else {
            arr[k] = right[j];
            j++;
        }
        ++k;
    }

    while(i < left.length) {
        arr[k] = left[i];
        k++;
        i++;
    }
    while(j < right.length) {
        arr[k] = right[j];
        k++;
        j++;
    }

}


let arr = [3,5,1,2,6,2,6,3,7,8,3];
mergeSort(arr);
console.log(arr);
//let arr: number[] = [1,2,3,4,5];
//console.log(arr.slice(0,arr.length/2));
//console.log(arr.slice(arr.length/2,arr.length));

