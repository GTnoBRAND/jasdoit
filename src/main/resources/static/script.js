const API_BASE = 'http://localhost:8080'; // Adjust if hosted elsewhere

fetch('/api/products')
.then(response => response.json())
.then(data => {
    const products = data.items;
    console.log("Products",products);
})

// 1. Add a product with images
async function addProduct(product, imageFiles) {
    const formData = new FormData();
    formData.append('product', new Blob([JSON.stringify(product)], { type: 'application/json' }));

    for (let i = 0; i < imageFiles.length; i++) {
        formData.append('imageFiles', imageFiles[i]);
    }

    const response = await fetch(`${API_BASE}/add-product`, {
        method: 'POST',
        body: formData
    });

    if (!response.ok) {
        const errorText = await response.text();
        throw new Error(`Failed to add product: ${errorText}`);
    }
    return await response.text();
}
//all products in the main page
fetch("http://localhost:8080/api/products/all-products")
    .then(response => response.json())
    .then(products => {
        const container = document.getElementById("product-list");
        products.forEach(p => {
            const productCard = document.createElement("div");
            productCard.innerHTML = `
                <h3>${p.name}</h3>
                <p>Price: $${p.price}</p>
                <button onclick="viewProduct(${p.id})">View Details</button>
            `;
            container.appendChild(productCard);
        });
    });

function viewProduct(id) {
    window.location.href = `product-details.html?id=${id}`;
}


async function fetchAndDisplayProducts() {
    const productList = document.getElementById('product-list');

    try {
        const response = await fetch("http://localhost:8080/api/products/all-products"); // Adjust if endpoint is different
        const products = await response.json();

        if (!Array.isArray(products)) {
            productList.innerHTML = "<p>⚠️ Failed to load products.</p>";
            return;
        }

        productList.innerHTML = "";

        for (const product of products) {
            const productCard = document.createElement("div");
            productCard.classList.add("product-item");

            // Create image container
            const imageContainer = document.createElement("div");

            if (product.images && product.images.length > 0) {
                product.images.forEach(img => {
                    const image = document.createElement("img");
                    image.src = `data:image/jpeg;base64,${img.base64Data}`;
                    image.style.width = "100%";
                    image.style.maxHeight = "200px";
                    image.style.objectFit = "cover";
                    imageContainer.appendChild(image);
                });
            } else {
                const placeholder = document.createElement("img");
                placeholder.src = "https://via.placeholder.com/300x200?text=No+Image";
                imageContainer.appendChild(placeholder);
            }

            const details = document.createElement("div");
            details.classList.add("product-details");

            details.innerHTML = `
    <h2>${product.name}</h2>
    <h3>$${product.price}</h3>
    <p>${product.description}</p>
    <p>Type: ${product.type}</p>
    <button onclick="viewProduct(${product.id})">View Details</button>
`;


            productCard.appendChild(imageContainer);
            productCard.appendChild(details);
            productList.appendChild(productCard);
        }


    } catch (error) {
        productList.innerHTML = "<p>❌ Error loading products: " + error.message + "</p>";
    }
}


//getBy id

document.getElementById('productForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const form = e.target;
    const product = {
        name: form.name.value,
        price: form.price.value,
        description: form.description.value,
        type: form.type.value,
        quantity: form.quantity.value
        // Add other fields as per your Product model
    };

    const imageFiles = form.images.files;

    try {
        const result = await addProduct(product, imageFiles);
        alert("Product added successfully!");
    } catch (err) {
        alert(err.message);
    }
}

);