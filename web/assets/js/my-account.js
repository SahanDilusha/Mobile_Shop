async  function loadFeatures() {

    const response = await fetch("loadFeatures");

    if (response.ok) {
        const json = response.json();

        if (json !== null) {

            const categoryList = json.categoryList;
            
            

        } else {

        }

    } else {

    }

}