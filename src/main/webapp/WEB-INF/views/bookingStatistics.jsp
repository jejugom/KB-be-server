<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title> ${lastMonth} 예약 건수 Top ${limit}</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        table {
            border-collapse: collapse;
            width: 60%;
            margin-top: 20px;
            font-family: Arial, sans-serif;
        }
        th, td {
            border: 1px solid #ccc; /* 얇고 은은한 회색 선 */
            padding: 8px 12px;
            text-align: center;
        }
        th {
            background-color: #f8f8f8;
            font-weight: bold;
        }
        tr:nth-child(even) {
            background-color: #fafafa;
        }
    </style>
</head>
<body>
<h2>${lastMonth} 예약 건수 Top ${limit}</h2>

<!-- 표 -->
<table border="1">
    <thead>
    <tr>
        <th>순위</th>
        <th>지점명</th>
        <th>예약 건수</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="stat" items="${topStats}" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${stat.branchName}</td>
            <td>${stat.bookingCount}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<!-- 그래프 -->
<canvas id="bookingChart" width="600" height="400"></canvas>

<script>
    const ctx = document.getElementById('bookingChart').getContext('2d');
    const labels = [
        <c:forEach var="stat" items="${topStats}" varStatus="status">
        '${stat.branchName}'<c:if test="${!status.last}">,</c:if>
        </c:forEach>
    ];
    const data = [
        <c:forEach var="stat" items="${topStats}" varStatus="status">
        ${stat.bookingCount}<c:if test="${!status.last}">,</c:if>
        </c:forEach>
    ];

    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: '${lastMonth} 예약 건수',
                data: data,
                backgroundColor: 'rgba(54, 162, 235, 0.6)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            }]
        },
        options: {
            responsive: false,
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: { stepSize: 1 }
                }
            }
        }
    });
</script>

</body>
</html>
