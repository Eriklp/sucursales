output "db_endpoint" {
  description = "The endpoint of the MySQL database"
  value       = aws_db_instance.franquicias_db.endpoint
}

output "db_name" {
  description = "The name of the MySQL database"
  value       = aws_db_instance.franquicias_db.db_name
}
