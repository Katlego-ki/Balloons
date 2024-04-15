//Use React!! Because the project is easy.

const modePics = {1:"images/Hot-Air-Balloon.jpg", 2:"images/Hot-Air-Balloon.jpg", 3:"images/Hot-Air-Balloon.jpg"}

let container = document.querySelector('.container');

//To select mode easy:id = 1, medium: id = 2, hard: id = 3
let selectMode = (modes) => {
    
    for(let i = 1; i <= modes; i++){
        let imageNode = document.createElement('img');
        container.appendChild(imageNode);
        imageNode.id = i;
        imageNode.src = modePics[i];
        imageNode.setAttribute('onclick','balloonsField(this.id)');
        container.appendChild(imageNode);
    }
}

selectMode(3);
//To Paint gray Balloons

let balloonsField = (mode) => {
    container.replaceChildren(); 
    switch(Number(mode)){
        case 1:  //Easy mode 4 ballons.
            for(let i = 0; i < 4; i++){
                const newDiv = document.createElement('div');
                newDiv.style.backgroundColor = 'gray';
                container.appendChild(newDiv);
            }
            break;
        case 2:  //medium 5 balloons
            for(let i = 0; i < 5; i++){
                const newDiv = document.createElement('div');
                newDiv.style.backgroundColor = 'gray';
                container.appendChild(newDiv);
            }
            break;
        case 3:  //hard 6 balloons
            for(let i = 0; i < 6; i++){
                const newDiv = document.createElement('div');
                newDiv.style.backgroundColor = 'gray';
                container.appendChild(newDiv);
            }
            break;
        default:
        break;
    }
}