<div>
    <h1>PD-Commerce</h1>
    <h2>Successful purchase</h2>
</div>

<div>
    <p>Thanks for purchase with us.</p>
    <p><strong>Your order with id ${order.id} has been successfully placed.</strong></p>
    <p>See the list below with your order details.</p>
</div>

<div>
    <table style="border-collapse: collapse;">
        <thead>
        <tr>
            <th style="padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #212121; color: white; border: 1px solid #ddd; padding: 8px;" scope="col">Product</th>
            <th style="padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #212121; color: white; border: 1px solid #ddd; padding: 8px;" scope="col">Qtd</th>
            <th style="padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #212121; color: white; border: 1px solid #ddd; padding: 8px;" scope="col">Price</th>
            <th style="padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #212121; color: white; border: 1px solid #ddd; padding: 8px;" scope="col">Total Price</th>
        </tr>
        </thead>
        <tbody class="table-group-divider">
        <#list order.products as products>
            <tr>
                <td style="border: 1px solid #ddd; padding: 8px;">${products.product}</td>
                <td style="border: 1px solid #ddd; padding: 8px;">${products.qtd}</td>
                <td style="border: 1px solid #ddd; padding: 8px;">${products.price}</td>
                <td style="border: 1px solid #ddd; padding: 8px;">${products.totalPrice}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>

<div>
    <p><strong>Delivery address:</strong> ${order.address}</p>
    <p><strong>Total order price:</strong> ${order.totalPrice}</p>
</div>