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
