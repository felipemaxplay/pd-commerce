<div>
    <h1 class="text-center">PD-Commerce</h1>
    <h2 class="text-center">Successful purchase</h2>
</div>

<div>
    <p class="text-center">Thanks for purchase with us.</p>
    <p class="text-center"><strong>Your order with id ${order.id} has been successfully placed.</strong></p>
    <p class="text-center">See the list below with your order details.</p>
</div>

<div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">Product</th>
            <th scope="col">Qtd</th>
            <th scope="col">Price</th>
            <th scope="col">Total Price</th>
        </tr>
        </thead>
        <tbody class="table-group-divider">
        <#list order.products as products>
            <tr>
                <td>${products.product}</td>
                <td>${products.qtd}</td>
                <td>${products.price}</td>
                <td>${products.totalPrice}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>

<div>
    <p><strong>Delivery address:</strong> ${order.address}</p>
    <p><strong>Total order price:</strong> ${order.totalPrice}</p>
</div>