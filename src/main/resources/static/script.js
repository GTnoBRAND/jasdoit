const API_BASE = 'http://localhost:8080'; // Adjust if hosted elsewhere

fetch('/api/products')
.then(response => response.json())
.then(data => {
    const products = data.items;
    console.log("Products",products);
})

function searchProducts() {
    const query = document.getElementById('searchInput').value.toLowerCase();
    const cards = document.querySelectorAll('.product-card');
    cards.forEach(card => {
        const match = card.textContent.toLowerCase().includes(query);
        card.style.display = match ? 'block' : 'none';
    });
}

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

// 2. Get all products
async function getAllProducts() {
    const response = await fetch(`${API_BASE}/all-products`);
    return await response.json();
}

// 3. Get one product by ID
async function getOneProduct(id) {
    const response = await fetch(`${API_BASE}/get-oneProduct?id=${id}`);
    if (!response.ok) throw new Error("Product not found");
    return await response.json();
}

// 4. Delete a product by ID
async function deleteProduct(id) {
    const response = await fetch(`${API_BASE}/delete-product?id=${id}`, {
        method: 'DELETE'
    });
    if (!response.ok) throw new Error("Failed to delete product");
}

// 5. Update a product (note: no image support here, just product fields)
async function updateProduct(product) {
    const response = await fetch(`${API_BASE}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(product)
    });
    return await response.json();
}

// 6. Get all images for a product by ID
async function getProductImages(id) {
    const response = await fetch(`${API_BASE}/product/${id}/images`);
    if (!response.ok) throw new Error("Failed to fetch images");
    return await response.json(); // This returns a list of Base64 image byte arrays
}

document.getElementById('productForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const form = e.target;
    const product = {
        name: form.name.value,
        price: form.price.value
        // Add other fields as per your Product model
    };

    const imageFiles = form.images.files;

    try {
        const result = await addProduct(product, imageFiles);
        alert("Product added successfully!");
    } catch (err) {
        alert(err.message);
    }
});