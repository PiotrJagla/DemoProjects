function mergeSort(arr: number[]) :number[] {
    let l:number = arr.length;
    let middle:number = l/2;
    if(middle = 0) {
        return arr;
    }

    let left: number[] = arr.slice(0, middle - 1); 
    let right: number[] = arr.slice(middle + 1, l);
    console.log(arr);
    console.log(left);
    console.log(right);
    mergeSort(left);
    mergeSort(right);
}


mergeSort([3,5,1,2,6,2,6,3,7,8,3]);
