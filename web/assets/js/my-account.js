var modelList;

async  function loadFeatures() {

    const response = await fetch("loadFeatures");

    if (response.ok) {
        const json = response.json();

        if (json !== null) {

            const categoryList = json.categoryList;
            
            const categoryListTag = document.getElementById("");
            
            categoryList.forEach(catogory =>{
                
                let tag = document.createElement("option");
                
            });

        } else {

        }

    } else {

    }

}

function updateModel() {
    
    let msodelSelectTag = document.getElementById("modelSelect");
    let selectedId =  document.getElementById("categorrySelect").value;
    msodelSelectTag.length = 1;
    
    modelList.forEach(model =>{
        
        if (model.category.id == selectedId) {
            
        }
        
    });
    
}